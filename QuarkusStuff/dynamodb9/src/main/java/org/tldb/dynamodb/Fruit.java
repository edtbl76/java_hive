package org.tldb.dynamodb;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

@Data
@RegisterForReflection
@NoArgsConstructor
public class Fruit {

    private String name;
    private String description;

    public static Fruit from(Map<String, AttributeValue> item) {
        Fruit fruit = new Fruit();
        if (item != null && !item.isEmpty()) {
            fruit.setName(item.get(AbstractService.FRUIT_NAME_COLUMN).s());
            fruit.setDescription(item.get(AbstractService.FRUIT_DESCRIPTION_COLUMN).s());
        }
        return fruit;
    }
}
