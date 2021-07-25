package com.doubleview.mq.rocket;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class SyncProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("my_producer_group");
        producer.setNamesrvAddr("127.0.0.1:9876");
        producer.setRetryTimesWhenSendFailed(5);
        producer.setSendLatencyFaultEnable(true);
        producer.start();
        for (int i = 0; i < 100; i++) {
            Message msg = new Message("TopicTest", null, ("Hello RocketMQ" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            msg.setDelayTimeLevel(1);
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }
}
