package com.itmuch.cloud.study.controller.second;


import com.itmuch.cloud.study.entity.second.Message;
import com.itmuch.cloud.study.repository.second.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    MessageRepository messageRepository;

    @GetMapping("/M/{id}")
    public Message findbyid(@PathVariable Long id) {
        Message findOne = this.messageRepository.findOne(id);
        return findOne;
    }
}
