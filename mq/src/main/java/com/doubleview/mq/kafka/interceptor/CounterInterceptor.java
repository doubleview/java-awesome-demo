package com.doubleview.mq.kafka.interceptor;

import cn.hutool.core.util.StrUtil;
import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

public class CounterInterceptor implements ProducerInterceptor<String, String> {

    private int successInt = 0;
    private int errorInt = 0;

    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        return record;
    }

    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
        if (exception == null) {
            successInt++;
        } else {
            errorInt++;
        }
    }

    @Override
    public void close() {
        System.out.println(StrUtil.format("successCount={}", successInt));
        System.out.println(StrUtil.format("errorCount={}", errorInt));
    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
