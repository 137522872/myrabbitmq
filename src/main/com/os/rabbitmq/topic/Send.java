package com.os.rabbitmq.topic;


import com.os.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 主题
 */
public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        String msg = "商品....";

        channel.basicPublish(EXCHANGE_NAME,"goods.add",null,msg.getBytes());

        System.out.println("------send good.add....");

        channel.close();
        connection.close();
    }
}
