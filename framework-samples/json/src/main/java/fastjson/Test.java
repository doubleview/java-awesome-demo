package fastjson;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.base.Splitter;
import org.apache.commons.io.FileUtils;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args) throws IOException {
        String content = FileUtils.readFileToString(new File("/Users/huchengchao/Downloads/aaa.json"), "UTF-8");
        JSONObject jsonObject = JSON.parseObject(content);
        JSONArray jsonArray = jsonObject.getJSONArray("data");

        List<String> list =
        Splitter.on(',').splitToList(FileUtils.readFileToString(new File("/Users/huchengchao/Downloads/bbb.json"), "UTF-8"));
        List<String> cityCodeList = list.stream().map(new Function<String, String>() {
            @Nullable
            @Override
            public String apply(@Nullable String input) {
                for (int i = 0; i < jsonArray.size(); i++) {
                    if (input.equals(jsonArray.getJSONObject(i).getString("name"))) {
                        return jsonArray.getJSONObject(i).getString("cityCode");
                    }
                }
                System.out.println(input);
                return null;
            }
        }).filter(Objects::nonNull).collect(Collectors.toList());
        cityCodeList.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });
        System.out.println(cityCodeList.stream().collect(Collectors.joining(",")));
    }
}
