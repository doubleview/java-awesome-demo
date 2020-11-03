package com.doubleview.mq.kafka.partitioner;

import cn.hutool.core.util.StrUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.Cluster;

import java.util.Map;
import java.util.Properties;

public class MyPartitioner implements Partitioner {

    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        return 1;
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }


    public static void main(String[] args) {
        Properties props = new Properties();
        //kafka 集群，broker-list
        props.put("bootstrap.servers", "192.168.31.101:9092");
        props.put("acks", "all");
        //重试次数
        props.put("retries", 3);
        //批次大小  16k, 注意默认情况下每个批次都会发送到同一个分区
        props.put("batch.size", 16384);
        //等待时间
        props.put("linger.ms", 1);
        //RecordAccumulator 缓冲区大小 32k
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 3000; i++) {
            //回调函数，该方法会在 Producer 收到 ack 时调用，为异步调用
            producer.send(new ProducerRecord<>("bigdata", Integer.toString(i)), (metadata, exception) -> {
                if (exception == null) {
                    System.out.println(StrUtil.format("success partition:{} offset:{}",
                            metadata.partition(), metadata.offset()));
                }else {
                    exception.printStackTrace();
                }
            });
        }
        //关闭资源
        producer.close();
    }
}
