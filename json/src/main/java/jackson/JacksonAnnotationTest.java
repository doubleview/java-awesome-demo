package jackson;

import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import org.junit.Test;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-28 ����3:53
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class JacksonAnnotationTest {

    @Test
    public void testJsonAnnotation() throws IOException {
        User user = new User();
        user.setName("huchengchao");
        user.setAge(24);
        user.setHeight(BigDecimal.TEN);
        user.setBirthDay(new Date());
        user.setCreateTime(new Date());


        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(Include.NON_NULL);

        System.out.println(mapper.writeValueAsString(user));

        user = mapper.readValue("{\n"
            + "  \"height\" : 10,\n"
            + "  \"birthDay\" : 1506585818376,\n"
            + "  \"createTime\" : 1506585818376,\n"
            + "  \"modifyTime\" : 1506585818376,\n"
            + "  \"MyAge\" : 24\n"
            + "}", User.class);
        System.out.println(user);
    }


    @Test
    public void testVisibility() throws JsonProcessingException {
        User user = new User();
        user.setName("huchengchao");
        user.setAge(24);
        user.setHeight(BigDecimal.TEN);
        user.setBirthDay(new Date());
        user.setCreateTime(new Date());


        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(Include.NON_NULL);

        //���л����е����ԣ�����private���͵�����
        mapper.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        System.out.println(mapper.writeValueAsString(user));
    }

}