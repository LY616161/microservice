package com.itmuch.cloud.study.controller;

import com.itmuch.cloud.study.entity.first.User;
import com.itmuch.cloud.study.entity.second.Message;
import com.itmuch.cloud.study.service.MessageService;
import com.itmuch.cloud.study.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Autowired
    UserService userService;
    @Autowired
    MessageService messageService;

    @PostMapping("/test")
    public void save(@RequestParam int age,
                     @RequestParam String name,
                     @RequestParam String content,
                     @RequestParam String name1){

        User user = new User();
        user.setAge(age);
        user.setName(name);
        Message message = new Message();
        message.setContent(content);
        message.setName(name1);
        userService.save(user);
        messageService.save(message);
    }

}
