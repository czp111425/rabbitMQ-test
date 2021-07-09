package com.telezone.test1;

import com.rabbitmq.client.*;
import com.telezone.utils.ConnectionUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class Recervier2 {
    private final static String QUEUE_NAME="test_work_queue";
    public static void main(String[] args) throws Exception{
        Connection connection = ConnectionUtil.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel){

            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
               // super.handleDelivery(consumerTag, envelope, properties, body);
                //String exchange = envelope.getExchange();
                String msg = new String(body);
                System.out.println(" [消费者2] received : " + msg + "!");
                channel.basicAck(envelope.getDeliveryTag(),false);

            }
        };
        channel.basicConsume(QUEUE_NAME,false,consumer);
    }
}
