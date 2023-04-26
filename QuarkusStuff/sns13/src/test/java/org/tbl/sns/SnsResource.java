package org.tbl.sns;


import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.services.sns.SnsClient;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SNS;
import static software.amazon.awssdk.regions.Region.US_EAST_1;

public class SnsResource implements QuarkusTestResourceLifecycleManager {

    public final static String TOPIC_NAME = "Quarkus";
    public final static String LOCALSTACK_VERSION = "localstack/localstack:0.11.1";
    public final static int SNS_PORT = 4566;

    private LocalStackContainer container;
    private SnsClient client;

    @Override
    public Map<String, String> start() {
        DockerClientFactory.instance().client();
        String topicArn;

        try {
            container = new LocalStackContainer(DockerImageName.parse(LOCALSTACK_VERSION)).withServices(SNS);
            container.start();

            StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(
                    AwsBasicCredentials.create("accessKey", "secretKey"));

            client = SnsClient.builder()
                    .endpointOverride(new URI(endpoint()))
                    .credentialsProvider(credentialsProvider)
                    .httpClientBuilder(UrlConnectionHttpClient.builder())
                    .region(US_EAST_1)
                    .build();

            topicArn = client.createTopic(builder -> builder.name(TOPIC_NAME)).topicArn();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to start LocalStack", e);
        }

        Map<String, String> properties = new HashMap<>();
        properties.put("quarkus.sns.endpoint-override", endpoint());
        properties.put("quarkus.sns.aws.region", "us-east-1");
        properties.put("quarkus.sns.aws.credentials.type", "static");
        properties.put("quarkus.sns.aws.credentials.static-provider.access-key-id", "accessKey");
        properties.put("quarkus.sns.aws.credentials.static-provider.secret-access-key", "secretKey");
        properties.put("topic.arn", topicArn);

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
                container.getMappedPort(SNS_PORT));
    }
}
