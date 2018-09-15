package com.os.rabbitmq.routing;

import com.os.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Send {

    private static final String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] args) throws IOException, TimeoutException {

        Connection connection = ConnectionUtils.getConnection();


        Channel channel = connection.createChannel();

        //声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME,"direct");

        String msg = "hello direct";

        //发送消息
        String routingKey = "error"; //指定路由key
        channel.basicPublish(EXCHANGE_NAME,routingKey,null,msg.getBytes());

        channel.close();
        connection.close();
    }
}
