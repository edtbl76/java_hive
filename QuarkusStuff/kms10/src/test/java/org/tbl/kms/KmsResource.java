package org.tbl.kms;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.DockerClientFactory;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.services.kms.KmsClient;

import java.util.HashMap;
import java.util.Map;

import static software.amazon.awssdk.regions.Region.*;
import static software.amazon.awssdk.services.kms.model.DataKeySpec.*;

public class KmsResource  implements QuarkusTestResourceLifecycleManager {

    private KmsContainer container;
    private KmsClient client;


    @Override
    public Map<String, String> start() {
        DockerClientFactory.instance().client();
        String masterKeyId;

        try {
            container = new KmsContainer();
            container.start();

            StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider
                    .create(AwsBasicCredentials.create("accessKey", "secretKey"));

            client = KmsClient.builder()
                    .endpointOverride(container.getEndpointOverride())
                    .credentialsProvider(credentialsProvider)
                    .httpClientBuilder(UrlConnectionHttpClient.builder())
                    .region(US_EAST_1)
                    .build();

            masterKeyId = client.createKey().keyMetadata().keyId();
            client.generateDataKey(builder -> builder.keyId(masterKeyId).keySpec(AES_256));

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to start localstack", e);
        }

        Map<String, String> properties = new HashMap<>();
        properties.put("quarkus.kms.endpoint-override", container.getEndpointOverride().toString());
        properties.put("quarkus.kms.aws.region", "us-east-1");
        properties.put("quarkus.kms.aws.credentials.type", "static");
        properties.put("quarkus.kms.aws.credentials.static-provider.access-key-id", "accessKey");
        properties.put("quarkus.kms.aws.credentials.static-provider.secret-access-key", "secretKey");
        properties.put("key.arn", masterKeyId);

        return properties;
    }

    @Override
    public void stop() {
        if (container != null) {
            container.close();
        }
    }
}
