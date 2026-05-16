package com.gladysz.jms.receiver;

import com.gladysz.jms.domain.Order;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

    @JmsListener(containerFactory = "jmsFactory", destination = "queue-test")
    public void receive(String message) {

        System.out.println("Received the message: " + message);
    }


    @JmsListener(containerFactory = "jmsFactory", destination = "queue-order")
    public void receiveOrder(@Payload Order order) {

        System.out.println("Received the order: " + order);
    }
}
