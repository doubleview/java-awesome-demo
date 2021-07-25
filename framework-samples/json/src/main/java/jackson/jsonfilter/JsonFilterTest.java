package jackson.jsonfilter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.PropertyWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.junit.Test;

/**
 * @author huchengchao.
 * @description:
 * @date: 2018-03-13 ÏÂÎç4:01
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class JsonFilterTest {


    @Test
    public void test() throws JsonProcessingException {
        PropertyFilter propertyFilter = new SimpleBeanPropertyFilter() {
            @Override
            public void serializeAsField(Object pojo, JsonGenerator jgen, SerializerProvider provider, PropertyWriter writer) throws Exception {
                if (include(writer)) {
                    if (!"intValue".equals(writer.getName())) {
                        writer.serializeAsField(pojo, jgen, provider);
                        return;
                    }
                    int intValue = ((MyDto) pojo).getIntValue();
                    if (intValue >= 0) {
                        writer.serializeAsField(pojo, jgen, provider);
                    }
                } else if (!jgen.canOmitFields()) {
                    writer.serializeAsOmittedField(pojo, jgen, provider);
                }
            }

            @Override
            protected boolean include(BeanPropertyWriter writer) {
                return true;
            }

            @Override
            protected boolean include(PropertyWriter writer) {
                return true;
            }
        };


        FilterProvider filters = new SimpleFilterProvider().addFilter("myFilter", propertyFilter);
        MyDto dtoObject = new MyDto();
        dtoObject.setIntValue(-1);

        ObjectMapper mapper = new ObjectMapper();
        String dtoAsString = mapper.writer(filters).writeValueAsString(dtoObject);
        System.out.println(dtoAsString);
    }
}
