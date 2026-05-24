package com.gladysz.jms.receiver;

import com.gladysz.jms.domain.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.gladysz.jms.configuration.QueueNames.*;


@Component
public class MessageReceiver {

    private static final Logger logger = LoggerFactory.getLogger(MessageReceiver.class);

    @JmsListener(containerFactory = "jmsFactory", destination = QUEUE_TEST)
    public void receive(String message) {

        logger.info("Received the message: {}", message);
    }


    @JmsListener(containerFactory = "jmsFactory", destination = QUEUE_ORDER)
    public void receiveOrder(@Payload Order order) {

        logger.info("Received the order: {}", order);
    }
}
