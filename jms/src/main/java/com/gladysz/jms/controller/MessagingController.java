package com.gladysz.jms.controller;

import com.gladysz.jms.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/messages")
public class MessagingController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping(path = "/process")
    public void processMessage(@RequestParam String message) {

        jmsTemplate.convertAndSend("queue-test", message);
    }


    @PostMapping(path = "/order/process")
    public void processOrder(@RequestBody Order order) {

        jmsTemplate.convertAndSend("queue-order", order);
    }
}
