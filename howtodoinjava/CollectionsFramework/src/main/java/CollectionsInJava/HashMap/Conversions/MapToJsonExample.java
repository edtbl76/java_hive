package CollectionsInJava.HashMap.Conversions;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;

public class MapToJsonExample {
    public static void main(String[] args) {
        HashMap<String, Object> hashmap = new HashMap<>();
        hashmap.put("id", 1);
        hashmap.put("firstName", "Ed");
        hashmap.put("lastName", "Mangini");

        ObjectMapper mapper = new ObjectMapper();
        try {
            String json = mapper.writeValueAsString(hashmap);
            System.out.println(json);
        } catch (JsonGenerationException | JsonMappingException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
