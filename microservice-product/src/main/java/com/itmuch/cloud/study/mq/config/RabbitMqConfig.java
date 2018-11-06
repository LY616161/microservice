package com.itmuch.cloud.study.mq.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;


@Configuration
public class RabbitMqConfig {
    @Bean
    public Queue Queue1() {
        return new Queue("Test1");
    }
    @Bean
    public Queue Queue2() {
        return new Queue("Test2");
    }
}
