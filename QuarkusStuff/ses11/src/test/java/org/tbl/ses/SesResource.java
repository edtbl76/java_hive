package org.tbl.ses;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.SES;
import static software.amazon.awssdk.regions.Region.US_EAST_1;

public class SesResource implements QuarkusTestResourceLifecycleManager {

    public final static String FROM_EMAIL = "from-quarkus@example.com";
    public final static String TO_EMAIL = "to-quarkus@example.com";
    public final static int SES_PORT = 4566;
    public final static String LOCALSTACK_VERSION = "localstack/localstack:0.11.1";

    private LocalStackContainer container;
    private SesClient client;

    @Override
    public Map<String, String> start() {
        DockerClientFactory.instance().client();

        try {
            container = new LocalStackContainer(DockerImageName.parse(LOCALSTACK_VERSION)).withServices(SES);
            container.start();

            StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider
                    .create(AwsBasicCredentials.create("accessKey", "secretKey"));

            client = SesClient.builder()
                    .endpointOverride(new URI(endpoint()))
                    .credentialsProvider(credentialsProvider)
                    .httpClientBuilder(UrlConnectionHttpClient.builder())
                    .region(US_EAST_1)
                    .build();

            client.verifyEmailIdentity(builder -> builder.emailAddress(FROM_EMAIL));
            client.verifyEmailIdentity(builder -> builder.emailAddress(TO_EMAIL));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to start LocalStack", e);
        }


        Map<String, String> properties = new HashMap<>();
        properties.put("quarkus.ses.endpoint-override", endpoint());
        properties.put("quarkus.ses.aws.region", "us-east-1");
        properties.put("quarkus.ses.aws.credentials.type", "static");
        properties.put("quarkus.ses.aws.credentials.static-provider.access-key-id", "accessKey");
        properties.put("quarkus.ses.aws.credentials.static-provider.secret-access-key", "secretKey");
        return properties;
    }

    @Override
    public void stop() {
        if (container != null) {
            container.close();
        }
    }

    private String endpoint()  {
        return String.format("http://%s:%s",
                container.getContainerIpAddress(),
                container.getMappedPort(SES_PORT));
    }
}
