package com.doubleview.hutool;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.db.Db;
import cn.hutool.db.Entity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DbUtils {

    public static void main(String[] args) throws SQLException {
        List<Entity> entities = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Entity entity = Entity.create("s2")
                    .set("key1", RandomUtil.randomString(RandomUtil.BASE_CHAR, 8))
                    .set("key2", RandomUtil.randomInt(1000000000))
                    .set("key3", RandomUtil.randomString(RandomUtil.BASE_CHAR, 8))
                    .set("key_part1", RandomUtil.randomString(RandomUtil.BASE_CHAR, 8))
                    .set("key_part2", RandomUtil.randomString(RandomUtil.BASE_CHAR, 8))
                    .set("key_part3", RandomUtil.randomString(RandomUtil.BASE_CHAR, 8))
                    .set("common_field", RandomUtil.randomString(RandomUtil.BASE_CHAR, 8));
            entities.add(entity);
        }
        Db.use().insert(entities);
        System.out.println("success");
    }
}