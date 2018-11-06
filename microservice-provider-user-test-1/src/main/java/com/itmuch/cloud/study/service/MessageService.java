package com.itmuch.cloud.study.service;

import com.itmuch.cloud.study.entity.second.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public void save(Message message){
        messageRepository.save(message);
    }
}
