package CollectionsInJava.HashMap.Conversions;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class JsonToMapExample {

    public static void main(String[] args) {

        String json = "{\"id\":1,\"name\":\"Ed Mangini\",\"age\":43,\"locatoini\":\"America\"}";

        HashMap<String, Object> map = new HashMap<>();

        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
            });
            System.out.println(map);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
