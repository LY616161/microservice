package com.itmuch.cloud.study.mq.controller;


import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class SendController {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @RequestMapping("/send")
    public String send(){
        String content = "provide" + new Date();
        amqpTemplate.convertAndSend("Test1",content);
        return content;
    }


    @RequestMapping("/multiSend")
    public String multiSend(){
        StringBuilder times=new StringBuilder();
        for(int i=0;i<10;i++){
            long time=System.nanoTime();
            amqpTemplate.convertAndSend("Test1","第"+i+"次发送的时间："+time);
            amqpTemplate.convertAndSend("Test2","第"+i+"次发送的时间："+time);
            times.append(time+"<br>");
        }
        return times.toString();
    }

    @RequestMapping("/topicSend1")
    public String  topicSend1() {
        String context = "my topic 1";
        System.out.println("发送者说 : " + context);
        this.amqpTemplate.convertAndSend("exchange", "topic.message", context);
        return context;
    }
    @RequestMapping("/topicSend2")
    public String topicSend2() {
        String context = "my topic 2";
        System.out.println("发送者说 : " + context);
        this.amqpTemplate.convertAndSend("exchange", "topic.messages", context);
        return  context;
    }


}
