package com.itmuch.cloud.study.service;

import com.itmuch.cloud.study.entity.first.User;
import com.itmuch.cloud.study.repository.first.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

//    @Transactional(value = "transactionManagerPrimary",rollbackFor = Exception.class)
    public void save(User user){
        userRepository.save(user);
    }

}
