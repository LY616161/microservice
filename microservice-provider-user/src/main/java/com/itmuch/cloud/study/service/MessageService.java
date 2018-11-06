package com.itmuch.cloud.study.service;

import com.itmuch.cloud.study.entity.second.Message;
import com.itmuch.cloud.study.repository.second.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    @Transactional(value = "transactionManagerSecondary",rollbackFor = Exception.class)
    public void save(Message message){
        messageRepository.save(message);
    }
}
