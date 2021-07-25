package com.doubleview.mq.rocket.transaction;

import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.TransactionMQProducer;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TransactionProducer {

    public static void main(String[] args) throws Exception {
        //接收RocketMQ回调的一个接口
        TransactionListenerImpl transactionListener = new TransactionListenerImpl();

        //创建一个支持事务消息的Producer
        TransactionMQProducer transactionMQProducer = new TransactionMQProducer("MyTransactionProducerGroup");
        transactionMQProducer.setNamesrvAddr("127.0.0.1:9876");

        //用来处理RocketMQ回调的一个线程池
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        transactionMQProducer.setExecutorService(executorService);
        transactionMQProducer.setTransactionListener(transactionListener);
        transactionMQProducer.start();

        Message message = new Message("TransactionTopic", "TestTag", "TestKey", "消息".getBytes(RemotingHelper.DEFAULT_CHARSET));
        //发送half消息
        SendResult sendResult = transactionMQProducer.sendMessageInTransaction(message, null);
        System.out.printf("%s%n", sendResult);
    }
}