package com.telezone.test1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.telezone.utils.ConnectionUtil;

public class Send {
    private final static String QUEUE_NAME = "test_work_queue";

    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        for(int i=0; i < 50; i++){
            String message = "task.. "+ i;
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes());
            System.out.println(" ăXă send "+message);
            Thread.sleep(i*2);
        }

        channel.close();
        connection.close();
    }
}
