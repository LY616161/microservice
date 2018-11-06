package com.itmuch.cloud.study.service;

import com.itmuch.cloud.study.entity.first.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public void save(User user){
        userRepository.save(user);
    }

}
