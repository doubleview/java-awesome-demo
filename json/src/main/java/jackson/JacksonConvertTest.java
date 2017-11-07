package jackson;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

/**
 * @author huchengchao.
 * @description:
 * @date: 2017-09-30 ÉÏÎç11:14
 * @Copyright: 2016 Hangzhou Enniu Tech Ltd. All rights reserved.
 */
public class JacksonConvertTest {


    @Test
    public void convertTest() throws JsonProcessingException {
        NodeBean nodeBean = new NodeBean();
        nodeBean.setId(1);
        nodeBean.setName("huchengchao");

        ObjectMapper mapper = new ObjectMapper();

        //Object-->JsonNode
        JsonNode node = mapper.valueToTree(nodeBean);
        System.out.println(node.get("id").asInt());
        System.out.println(node.get("name").asText());

        //JsonNode-->Object
        nodeBean = mapper.treeToValue(node, NodeBean.class);
        System.out.println(nodeBean.getId());
        System.out.println(nodeBean.getName());
    }


    private static  class NodeBean{
        private Integer id;
        private String name;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}