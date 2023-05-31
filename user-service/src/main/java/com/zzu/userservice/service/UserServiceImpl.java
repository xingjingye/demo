package com.zzu.userservice.service;


import com.zzu.general.interfaces.UserService;
import com.zzu.general.pojo.User;
import com.zzu.userservice.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryById(Long id) {
        return userMapper.findById(id);
    }
}