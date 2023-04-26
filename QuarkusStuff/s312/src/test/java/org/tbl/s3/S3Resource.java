package org.tbl.s3;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.S3;
import static software.amazon.awssdk.regions.Region.US_EAST_1;

public class S3Resource implements QuarkusTestResourceLifecycleManager {

    private final static String BUCKET_NAME = "quarkus.test.bucket";
    private final static String LOCALSTACK_VERSION = "localstack/localstack:0.11.1";
    private final static int S3_PORT = 4566;

    private LocalStackContainer container;
    private S3Client client;

    @Override
    public Map<String, String> start() {
        DockerClientFactory.instance().client();

        try {
            container = new LocalStackContainer(DockerImageName.parse(LOCALSTACK_VERSION)).withServices(S3);
            container.start();

            StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider
                    .create(AwsBasicCredentials.create("accessKey", "secretKey"));

            client = S3Client.builder()
                    .endpointOverride(new URI(endpoint()))
                    .credentialsProvider(credentialsProvider)
                    .httpClientBuilder(UrlConnectionHttpClient.builder())
                    .region(US_EAST_1)
                    .build();

            client.createBucket(builder -> builder.bucket(BUCKET_NAME));
        } catch (Exception e) {
            throw new RuntimeException("Failed to start LocalStack", e);
        }

        Map<String, String> properties = new HashMap<>();
        properties.put("quarkus.s3.endpoint-override", endpoint());
        properties.put("quarkus.s3.aws.region", "us-east-1");
        properties.put("quarkus.s3.aws.credentials.type", "static");
        properties.put("quarkus.s3.aws.credentials.static-provider.access-key-id", "accessKey");
        properties.put("quarkus.s3.aws.credentials.static-provider.secret-access-key", "secretKey");
        properties.put("bucket.name", BUCKET_NAME);

        return properties;
    }

    @Override
    public void stop() {
        if (container != null) {
            container.close();
        }
    }

    private String endpoint() {
        return String.format("http://%s:%s",
                container.getContainerIpAddress(),
                container.getMappedPort(S3_PORT));
    }

}
