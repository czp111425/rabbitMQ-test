package com.telezone.test;

import com.rabbitmq.client.*;
import com.telezone.utils.ConnectionUtil;

import java.io.IOException;

public class Receiver2 {
    private final static String QUEUE_NAME = "simple_queue1";

    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false, false,false,null);
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
               // super.handleDelivery(consumerTag, envelope, properties, body);

                String exchange = envelope.getExchange();
                Long delivery = envelope.getDeliveryTag();
                //int res = 1/0;
                String msg = new String(body);
                System.out.println(" [x] received : " + msg + "!");
                //channel.basicAck(delivery,false);
            }
        };
        channel.basicConsume(QUEUE_NAME, false, defaultConsumer);
    }
}
