package org.tldb.dynamodb;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FruitSyncService extends AbstractService {

    @Inject
    DynamoDbClient dynamoDbClient;

    public List<Fruit> findAll() {
        return dynamoDbClient.scanPaginator(scanRequest())
                .items().stream()
                .map(Fruit::from)
                .collect(Collectors.toList());
    }

    public Fruit get(String name) {
        return Fruit.from(dynamoDbClient.getItem(getItemRequest(name)).item());

    }

    public List<Fruit> add(Fruit fruit) {
        dynamoDbClient.putItem(putItemRequest(fruit));
        return findAll();
    }
}
