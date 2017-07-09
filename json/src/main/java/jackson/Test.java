package jackson;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by doubleview on 2017/7/9.
 */
public class Test {

    public static void main(String[] args) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        People people = new People();
        people.setName("hcc");
        people.setAge(24);
        System.out.println(mapper.writeValueAsString(people));

        Map<String, Object> map = mapper.readValue("{\"name\":\"hcc\",\"age\":24}", Map.class);
        System.out.println(map);

        People convertPeople = mapper.convertValue(map, People.class);
        System.out.println(convertPeople);

        List<String> list = mapper.readValue("[1,2,3]", List.class);
        System.out.println(list);

        JsonNode jsonNode = mapper.readTree("{\"name\":\"hcc\",\"age\":24}");
        System.out.println(jsonNode.get("name").asText());
    }
}
