package jackson;


import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.sql.Timestamp;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by doubleview on 2017/7/9.
 */
public class JacksonTest {

    private ObjectMapper mapper;

    @Before
    public void init() {
        this.mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Test
    public void testWriteJson() throws IOException {
        //不输出值null的属性
        mapper.setSerializationInclusion(Include.NON_NULL);
        People people = new People();
        people.setName("hcc");
        people.setAge(24);
        people.setCreateTime(new Date());
        people.setLastModifyTime(new Date());
        people.setBirthday(new Date());
        people.setGender(1);
        people.setIdCardNo("340121199310044612");
        people.setTimestamp(new Timestamp(System.currentTimeMillis()));
        people.setaDouble(3.141592D);

        //输出json
        System.out.println(mapper.writeValueAsString(people));

        //输出到文件中
        mapper.writeValue(new File("result.json") , people);

        List<People> peopleList = new ArrayList<>();
        peopleList.add(people);
        mapper.writeValue(new File("resultList.json") , peopleList);
    }


    @Test
    public void testReadJson() throws IOException {
        //从文件中读取
        People people = mapper.readValue(new File("result.json"), People.class);
        System.out.println(people);

        Map<String, String> map = mapper.readValue(new File("result.json"), new TypeReference<Map<String , String>>(){});
        System.out.println(map);

        List<People> peopleList = mapper.readValue(new File("resultList.json"), new TypeReference<List<People>>(){});
        System.out.println(peopleList);

        //默认将对象型json格式转换为Map类型
        List resultList = mapper.readValue(new File("resultList.json"), List.class);
        System.out.println(resultList.get(0).getClass());
        System.out.println(resultList);

        //从字符串中读取并解析
        people = mapper.readValue("{\"name\":\"hcc\",\"age\":24,\"photo\":\"my.jpg\","
            + "\"idCardNo\":\"340121199310044612\",\"gender\":1,\"birthday\":1506580198051,\"createTime\":1506580198051,"
            + "\"lastModifyTime\":1506580198051,\"onlyMethod\":\"onlyMethod\"}", People.class);
        System.out.println(people);


    }


    @Test
    public void testProcessJsonNode() throws IOException {
        JsonNode node = mapper.readTree(new File("result.json"));
        System.out.println(node.get("name").asText());
        System.out.println(node.get("age").asInt());

        JsonNode arrayNode = mapper.readTree(new File("resultList.json"));
        System.out.println(arrayNode.get(0).get("name").asText());
        System.out.println(arrayNode.get(0).get("age").asInt());
    }


    @Test
    public void testGeneratorAndParser() throws IOException {
        JsonFactory factory = mapper.getFactory();
        StringWriter sw = new StringWriter();
        JsonGenerator generator = factory.createGenerator(sw);
        generator.writeStartObject();
        generator.writeStringField("name" , "huchengchao");
        generator.writeNumberField("age" , 24);

        generator.writeFieldName("gift");
        generator.writeStartArray();
        generator.writeString("caddy");
        generator.writeString("egg");
        generator.writeEndArray();

        generator.writeFieldName("animal");
        generator.writeStartObject();
        generator.writeStringField("name" , "blackTiger");
        generator.writeEndObject();
        generator.writeEndObject();
        generator.close();
        System.out.println(sw.toString());

        JsonParser parser = factory.createParser("{\"name\":\"huchengchao\",\"age\":24,\"gift\":[\"caddy\",\"egg\"],\"animal\":{\"name\":\"blackTiger\"}}");
        JsonToken token = parser.nextToken();
        token = parser.nextToken();
        if ((token != JsonToken.FIELD_NAME)) {
            System.out.println("error");
        }

        token = parser.nextToken();
        if (token != JsonToken.VALUE_STRING) {
            System.out.println("error");
        }

        System.out.println("my name is " + parser.getText());
        parser.close();
    }


    @Test
    public void testSerializationFeature() throws JsonProcessingException {
        People people = new People();
        people.setName("hcc");
        people.setAge(24);
        people.setCreateTime(new Date());
        people.setLastModifyTime(new Date());
        people.setBirthday(new Date());
        people.setGender(1);
        people.setIdCardNo("340121199310044612");

        //格式化输出，默认disable
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        System.out.println(mapper.writeValueAsString(people));

        //在POJO对象没有可序列化的属性时，会抛出异常，默认enable
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        System.out.println(mapper.writeValueAsString(new Object()));

        //将时间属性以timestamp的形式输出，默认enable
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        System.out.println(mapper.writeValueAsString(people));
    }


    @Test
    public void testDeserializationFeature() throws IOException {
        //在遇到未知属性时，会抛出异常, 默认enable
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //允许将空字符串"" 作为空对象处理，默认disable
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        People people = mapper.readValue("{\"name\":\"hcc\",\"age\":24,\"photo\":\"my.jpg\","
            + "\"idCardNo\":\"340121199310044612\",\"gender\":1,\"birthday\":1506580198051,\"createTime\":1506580198051,"
            + "\"lastModifyTime\":1506580198051,\"onlyMethod\":\"onlyMethod\",\"other\":\"other\"}", People.class);
        System.out.println(people);
    }


    @Test
    public void testGeneratorAndParserFeature() {
        //允许一些C/C++样式的注释存在
        mapper.configure(Feature.ALLOW_COMMENTS, true);
        //允许非引用字段名存在
        mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        //允许单引号字段存在
        mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);

        //强制去除非ASCII字符
        mapper.configure(JsonGenerator.Feature.ESCAPE_NON_ASCII, true);
    }
}