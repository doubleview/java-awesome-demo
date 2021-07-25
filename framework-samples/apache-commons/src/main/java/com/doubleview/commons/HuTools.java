package com.doubleview.commons;

import cn.hutool.http.HttpUtil;

public class HuTools {

    public static void main(String[] args) {
        System.out.println(HttpUtil.get("https://www.baidu.com"));
    }
}
