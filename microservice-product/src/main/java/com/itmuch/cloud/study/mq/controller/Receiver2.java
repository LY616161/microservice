package com.itmuch.cloud.study.mq.controller;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = "Test2")
public class Receiver2 {
    @RabbitHandler
    public void receiver(String msg) {
        System.out.println("Test1 receiver2:" + msg);
    }
}
