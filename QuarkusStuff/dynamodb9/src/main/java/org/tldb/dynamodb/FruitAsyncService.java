package org.tldb.dynamodb;

import io.smallrye.mutiny.Uni;
import software.amazon.awssdk.services.dynamodb.DynamoDbAsyncClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class FruitAsyncService extends AbstractService{

    @Inject
    DynamoDbAsyncClient dynamoDbAsyncClient;

    public Uni<List<Fruit>> findAll() {
        return Uni.createFrom().completionStage(() -> dynamoDbAsyncClient.scan(scanRequest()))
                .onItem()
                .transform(scanResponse -> scanResponse.items()
                                .stream()
                                .map(Fruit::from)
                                .collect(Collectors.toList()));
    }

    public Uni<List<Fruit>> add(Fruit fruit) {
        return Uni.createFrom().completionStage(() -> dynamoDbAsyncClient.putItem(putItemRequest(fruit)))
                .onItem()
                .ignore()
                .andSwitchTo(this::findAll);
    }

    public Uni<Fruit> get(String name) {
        return Uni.createFrom().completionStage(() -> dynamoDbAsyncClient.getItem(getItemRequest(name)))
                .onItem()
                .transform(getItemResponse -> Fruit.from(getItemResponse.item()));
    }
}
