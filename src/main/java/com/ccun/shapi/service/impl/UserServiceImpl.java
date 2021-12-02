package com.ccun.shapi.service.impl;

import com.ccun.shapi.entity.User;
import com.ccun.shapi.repository.UserRepository;
import com.ccun.shapi.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserRepository userRepository;

    @Override
    public User selectUserByToken(String token) {
        return userRepository.findUserByToken(token);
    }

    @Override
    public User selectUserById(Integer id) {
        return userRepository.findUserById(id);
    }
}
