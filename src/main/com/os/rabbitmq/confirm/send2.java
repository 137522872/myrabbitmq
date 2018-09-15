package com.os.rabbitmq.confirm;

import com.os.rabbitmq.ConnectionUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class send2 {

    private static final String QUEUE_NAME = "test_queue_confirm1";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        Connection connection = ConnectionUtils.getConnection();

        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);

        //生产者调用confirmSelect 将channel设置为confirm模式
        channel.confirmSelect();

        String msg = null;


        // 批量发送 然后在确认
        for(int i= 0 ;i<10;i++){

            msg= "hello confrim " + i;
            channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        }


        if(!channel.waitForConfirms()){
            System.out.println("message send[2] failed ");
        }else {
            System.out.println("message send[2] ok....");
        }

        channel.close();
        connection.close();
    }
}
