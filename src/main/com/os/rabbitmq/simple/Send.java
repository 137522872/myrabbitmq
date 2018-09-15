package com.os.rabbitmq.simple;

import com.os.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 简单队列
 */
public class Send {

    private static final String QUEUE_NAME = "test_simple_queue";
    public static void main(String[] args) throws IOException, TimeoutException {

        //获取一个连接
        Connection connection = ConnectionUtils.getConnection();

        //创建一个通道
        Channel channel = connection.createChannel();

        //创建一个队列
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //构建一条消息
        String msg = "hello simple 简单队列";

        //发布消息
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());

        channel.close();
        connection.close();
    }
}
