package gson;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.junit.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by doubleview on 2017/5/21.
 */
public class TestGson {


    @Test
    public void test1() {
        Gson gson = new Gson();
        User user = new User();
        user.setId(1);
        user.setName("hcc");
        //user.setAge(23);

        Order order = new Order();
        order.setId(2);
        order.setNo("20170909");
        order.setOrderTime(new Date());

        Map<String, Object> map = new HashMap<>();
        map.put("user", user);
        map.put("order", order);

         
//        System.out.println(json);


        JsonParser parser = new JsonParser();
//        JsonObject object = (JsonObject) parser.parse(json);

//        JsonObject userObject = object.getAsJsonObject("user");
//        JsonObject orderObject = object.getAsJsonObject("order");
//        User userResult = gson.fromJson(userObject, User.class);
//        Order orderResult = gson.fromJson(orderObject, Order.class);
//        System.out.println(userResult);
//        System.out.println(orderResult);
    }
}
