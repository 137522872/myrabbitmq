package com.os.rabbitmq.workfair;

import com.os.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 工作队列模型
 *
 *               |---- c1
 *      p---queue|
 *               |---- c2
 *
 */
public class Send {

    private static final  String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws InterruptedException {

        try {

            // 获取连接
            Connection connection = ConnectionUtils.getConnection();

            //获取channel
            Channel channel = connection.createChannel();

            channel.queueDeclare(QUEUE_NAME,false,false,false,null);

            //每个消费者 发送确认消息之前 消息队列不发送下一个消息到消费者 限行发送给同一个消费者不的超过一条
            channel.basicQos(1);

            for (int i = 0; i<50 ; i++) {

                String msg  = "hello "+ i;

                channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());

                Thread.sleep(100);
            }

            channel.close();
            connection.close();

        } catch (IOException e) {

            e.printStackTrace();
        } catch (TimeoutException e) {

            e.printStackTrace();
        }


    }
}
