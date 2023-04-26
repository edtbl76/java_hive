package org.tlb.dynamodb;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.DockerClientFactory;
import org.testcontainers.containers.localstack.LocalStackContainer;
import org.testcontainers.utility.DockerImageName;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static org.testcontainers.containers.localstack.LocalStackContainer.Service.DYNAMODB;
import static software.amazon.awssdk.regions.Region.*;

public class DynamoResource implements QuarkusTestResourceLifecycleManager {

    public final static String TABLE_NAME = "QuarkusFruits";

    private LocalStackContainer container;
    private DynamoDbClient client;

    @Override
    public Map<String, String> start() {
        DockerClientFactory.instance().client();
        try {
            container = new LocalStackContainer(DockerImageName.parse("localstack/localstack:latest"))
                    .withServices(DYNAMODB);
            container.start();

            client = DynamoDbClient.builder()
                    .endpointOverride(new URI(endpoint()))
                    .credentialsProvider(StaticCredentialsProvider
                            .create(AwsBasicCredentials.create("accessKey", "secretKey")))
                    .httpClientBuilder(UrlConnectionHttpClient.builder())
                    .region(US_EAST_1)
                    .build();


            //noinspection unchecked
            client.createTable(tableRequest ->
                    tableRequest.tableName(TABLE_NAME)
                            .keySchema(keySchema -> keySchema.attributeName("fruitName")
                                    .keyType(KeyType.HASH))
                            .attributeDefinitions(attrDefs -> attrDefs.attributeName("fruitName")
                                    .attributeType(ScalarAttributeType.S))
                            .provisionedThroughput(thruput -> thruput.readCapacityUnits(1L).writeCapacityUnits(1L))
            );

        } catch (Exception e) {
            throw new RuntimeException("Failed to start DynamoDb localstack server", e);
        }


        Map<String, String> properties = new HashMap<>();
        properties.put("quarkus.dynamodb.endpoint-override", endpoint());
        properties.put("quarkus.dynamodb.aws.region", "us-east-1");
        properties.put("quarkus.dynamodb.aws.credentials.type", "static");
        properties.put("quarkus.dynamodb.aws.credentials.static-provider.access-key-id", "accessKey");
        properties.put("quarkus.dynamodb.aws.credentials.static-provider.secret-access-key", "secretKey");

        return properties;
    }

    @Override
    public void stop() {
        if (container != null) {
            container.close();
        }
    }

    private String endpoint() {
        return String.format("http://%s:%s", container.getContainerIpAddress(), container.getMappedPort(
                4566));
    }
}
