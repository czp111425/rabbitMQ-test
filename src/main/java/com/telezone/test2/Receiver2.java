package com.telezone.test2;

import com.rabbitmq.client.*;
import com.telezone.utils.ConnectionUtil;

import java.io.IOException;

public class Receiver2 {
    private final static String EXCHANGE_NAME = "test_fanout_exchange";
    private final  static String QUEUE_ANME = "fanout_exchange_queue_mail";

    public static void main(String[] args) throws  Exception{
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_ANME,false,false,false,null);
        channel.queueBind(QUEUE_ANME,EXCHANGE_NAME,"");
        DefaultConsumer consumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                //super.handleDelivery(consumerTag, envelope, properties, body);
                String msg = new String(body);
                System.out.println(" [邮箱服务] receiver:"+msg);
            }
        };
        channel.basicConsume(QUEUE_ANME,true,consumer);
    }
}
