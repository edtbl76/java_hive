package org.tbl.sqs;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.services.sqs.SqsClient;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SQS;
import static software.amazon.awssdk.regions.Region.US_EAST_1;

public class SqsResource implements QuarkusTestResourceLifecycleManager {

    public final static String QUEUE_NAME = "Quarkus";
    public final static int SQS_PORT = 4566;
    public final static String LOCALSTACK_VERSION = "localstack/localstack:0.11.1";

    private LocalStackContainer container;
    private SqsClient client;

    @Override
    public Map<String, String> start() {
        DockerClientFactory.instance().client();
        String queueUrl;

        try {
            container = new LocalStackContainer(DockerImageName.parse(LOCALSTACK_VERSION)).withServices(SQS);
            container.start();

            StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(
                    AwsBasicCredentials.create("accessKey", "secretKey"));

            client = SqsClient.builder()
                    .endpointOverride(new URI(endpoint()))
                    .credentialsProvider(credentialsProvider)
                    .httpClientBuilder(UrlConnectionHttpClient.builder())
                    .region(US_EAST_1)
                    .build();

            queueUrl = client.createQueue(builder -> builder.queueName(QUEUE_NAME)).queueUrl();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to start LocalStack", e);
        }

        Map<String, String> properties = new HashMap<>();
        properties.put("quarkus.sqs.endpoint-override", endpoint());
        properties.put("quarkus.sqs.aws.region", "us-east-1");
        properties.put("quarkus.sqs.aws.credentials.type", "static");
        properties.put("quarkus.sqs.aws.credentials.static-provider.access-key-id", "accessKey");
        properties.put("quarkus.sqs.aws.credentials.static-provider.secret-access-key", "secretKey");
        properties.put("queue.url", queueUrl);

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
                container.getMappedPort(SQS_PORT));
    }
}
