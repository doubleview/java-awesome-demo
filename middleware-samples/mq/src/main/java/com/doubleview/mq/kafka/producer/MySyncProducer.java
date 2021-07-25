package com.doubleview.mq.kafka.producer;

import cn.hutool.core.util.StrUtil;
import org.apache.kafka.clients.producer.*;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

public class MySyncProducer {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Properties props = new Properties();
        //kafka 集群，broker-list
        props.put("bootstrap.servers", "ecs01:9092");
        props.put("acks", "all");
        //重试次数
        props.put("retries", 3);
        //批次大小  16k
        props.put("batch.size", 16384);
        //等待时间
        props.put("linger.ms", 1000);
        props.put("max.block.ms", 600000000);
        //RecordAccumulator 缓冲区大小 32M
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<>("first", Integer.toString(i), Integer.toString(i)), (metadata, exception) -> {
                if (exception == null) {
                    System.out.println(StrUtil.format("success partition:{} offset:{}", metadata.partition(), metadata.offset()));
                } else {
                    exception.printStackTrace();
                }
            }).get();
        }
        //关闭资源
        producer.close();
    }
}