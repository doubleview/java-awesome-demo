package com.doubleview.mq.rocket.transaction;

import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

public class TransactionListenerImpl implements TransactionListener {

    //如果half消息发送成功了, 就会在这里回调你的接口
    //可以在这里执行本地事务
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        return LocalTransactionState.COMMIT_MESSAGE;
    }

    //如果没有commit或者rollback消息，RocketMQ就会回调这个接口
    //
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        return LocalTransactionState.COMMIT_MESSAGE;
    }
}