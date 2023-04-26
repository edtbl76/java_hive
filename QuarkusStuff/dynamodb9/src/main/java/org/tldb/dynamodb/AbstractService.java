package org.tldb.dynamodb;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractService {

    public static final String FRUIT_NAME_COLUMN = "fruitName";
    public static final String FRUIT_DESCRIPTION_COLUMN = "fruitDescription";

    public String getTableName() {
        return "QuarkusFruits";
    }

    protected ScanRequest scanRequest() {
        return ScanRequest.builder()
                .tableName(getTableName())
                .attributesToGet(FRUIT_NAME_COLUMN, FRUIT_DESCRIPTION_COLUMN)
                .build();
    }

    protected PutItemRequest putItemRequest(Fruit fruit) {
        Map<String, AttributeValue> item = new HashMap<>();
        item.put(FRUIT_NAME_COLUMN, AttributeValue.builder().s(fruit.getName()).build());
        item.put(FRUIT_DESCRIPTION_COLUMN, AttributeValue.builder().s(fruit.getDescription()).build());

        return PutItemRequest.builder()
                .tableName(getTableName())
                .item(item)
                .build();
    }

    protected GetItemRequest getItemRequest(String name) {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(FRUIT_NAME_COLUMN, AttributeValue.builder().s(name).build());

        return GetItemRequest.builder()
                .tableName(getTableName())
                .key(key)
                .attributesToGet(FRUIT_NAME_COLUMN, FRUIT_DESCRIPTION_COLUMN)
                .build();
    }
}
