package com.doubleview.mq.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class MyProducer {

    public static void main(String[] args) {
        Properties props = new Properties();
        //kafka 集群，broker-list
        props.put("bootstrap.servers", "ecs01:9092");
        props.put("acks", "all");

        //重试次数
        props.put("retries", 3);
        //重试间隔 100ms
        props.put("retry.backoff.ms", 100);

        //等待时间
        props.put("linger.ms", 1);
        //批次大小  16k
        props.put("batch.size", 16 * 1024);

        //RecordAccumulator 缓冲区大小 32M
        props.put("buffer.memory", 32 * 1024 * 1024);
        //缓冲区填满之后的阻塞时间, 60s
        props.put("max.block.ms", 60 * 1000);

        //每个请求的最大大小
        props.put("max.request.size", 1024 * 1024);

        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 100; i++) {
            producer.send(new ProducerRecord<>("bigdata", null, Integer.toString(i)));
        }
        //关闭资源
        producer.close();
    }
}