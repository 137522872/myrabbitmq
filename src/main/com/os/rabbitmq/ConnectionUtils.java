package com.os.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 建立连接工具类
 *
 */
public class ConnectionUtils {


    /**
     * 获取MQ的连接
     * @return
     */
    public static Connection getConnection() throws IOException, TimeoutException {

        //定义一个连接工厂
        ConnectionFactory factory = new ConnectionFactory();

        //设置服务地址
        factory.setHost("127.0.0.1");
        //设置端口号 AMPQ协议
        factory.setPort(5672);

        //设置连接的vhost
        factory.setVirtualHost("/vhost_mmr");

        //设置用户名
        factory.setUsername("os");
        //设置密码
        factory.setPassword("123456");

        // 获取连接
        Connection connection = factory.newConnection();

        return connection;
    }

}
