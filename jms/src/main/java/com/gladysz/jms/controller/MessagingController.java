package com.gladysz.jms.controller;

import com.gladysz.jms.domain.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

import static com.gladysz.jms.configuration.QueueNames.*;

@RestController
@RequestMapping("/messages")
public class MessagingController {

    private final JmsTemplate jmsTemplate;


    public MessagingController(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }


    @PostMapping(path = "/process")
    public ResponseEntity<String> processMessage(@RequestParam String message) {

        jmsTemplate.convertAndSend(QUEUE_TEST, message);

        return ResponseEntity.ok("Message has been sent to queue");
    }


    @PostMapping(path = "/order/process")
    public ResponseEntity<String> processOrder(@RequestBody Order order) {

        jmsTemplate.convertAndSend(QUEUE_ORDER, order);

        return ResponseEntity.ok("Order has been sent to queue");
    }
}
