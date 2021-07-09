package com.telezone.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.telezone.utils.ConnectionUtil;

public class Send {
    private  final static String Queue_Name = "simple_queue1";
    public static void main(String[] args) throws Exception {
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(Queue_Name,false,false,false,null);
        String message = "nihao ";
        channel.basicPublish("",Queue_Name,null,message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();

    }
}
