package com.doubleview.mq.kafka.consumer;

import cn.hutool.core.util.StrUtil;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class MyConsumer {

    public static void main(String[] args) {
        // 创建消费者的配置信息
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.31.101:9092");
        properties.put("group.id", "bigdata");

        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.intervals.ms", "1000");

        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //只有在换组或offset过期的情况下生效
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        KafkaConsumer kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("bigdata"));
        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(StrUtil.format("offset={} key={} value={}",
                        consumerRecord.offset(), consumerRecord.key(), consumerRecord.value()));
            }
        }
    }
}
