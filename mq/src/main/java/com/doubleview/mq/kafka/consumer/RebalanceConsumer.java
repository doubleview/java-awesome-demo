package com.doubleview.mq.kafka.consumer;

import cn.hutool.core.util.StrUtil;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;

import java.time.Duration;
import java.util.*;

/**
 * 自定义提交offset
 */
public class RebalanceConsumer {

    private static Map<TopicPartition, Long> currentOffset = new HashMap<>();

    public static void main(String[] args) {
        // 创建消费者的配置信息
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "192.168.31.101:9092");
        properties.put("group.id", "bigdata");

//        properties.put("enable.auto.commit", "true");
        properties.put("auto.commit.intervals.ms", "1000");

        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        //只有在换组或offset过期的情况下生效
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        KafkaConsumer kafkaConsumer = new KafkaConsumer<>(properties);
        kafkaConsumer.subscribe(Arrays.asList("bigdata"), new ConsumerRebalanceListener() {
            //会在rebalance之前调用
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                commitOffset(currentOffset);
            }

            //会在rebalance之后调用
            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {
                currentOffset.clear();
                for (TopicPartition partition : partitions) {
                    kafkaConsumer.seek(partition, getOffset(partition));// 定位到最近提交的 offset 位置继续消费
                }
            }
        });

        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(StrUtil.format("offset={} key={} value={}",
                        consumerRecord.offset(), consumerRecord.key(), consumerRecord.value()));
                currentOffset.put(new TopicPartition(consumerRecord.topic(), consumerRecord.partition()), consumerRecord.offset());
            }
            //异步提交
            commitOffset(currentOffset);
        }
    }

    //获取某分区的最新 offset
    private static long getOffset(TopicPartition partition) {
        return 0;
    }
    //提交该消费者所有分区的 offset
    private static void commitOffset(Map<TopicPartition, Long> currentOffset) {
        System.out.println("start commit offset");
    }
}
