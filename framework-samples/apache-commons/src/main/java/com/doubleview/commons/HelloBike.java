package com.doubleview.commons;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

public class HelloBike {

    public static void main(String[] args) throws IOException {
        String str = FileUtils.readFileToString(new File("/Users/huchengchao/Downloads/r2.json"), Charset.defaultCharset());
        JSONArray jsonArray = JSONUtil.parseArray(str);
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            JSONObject result = JSONUtil.parseObj(jsonObject.getJSONObject("_source").getStr("requestJson"));
            System.out.println(result.getStr("arg0"));
        }
    }
}