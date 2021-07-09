package com.telezone.utils;


import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class ConnectionUtil {

    public static Connection getConnection(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("10.161.2.18");
        factory.setPort(5672);
        factory.setVirtualHost("testlocal");
        factory.setUsername("root");
        factory.setPassword("root");
        Connection connection = null;
        try {
            connection = factory.newConnection();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

        return connection;


    }
}

