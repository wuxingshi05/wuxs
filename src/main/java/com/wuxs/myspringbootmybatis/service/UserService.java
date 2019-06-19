package com.wuxs.myspringbootmybatis.service;

import com.wuxs.myspringbootmybatis.domain.User;
import com.wuxs.myspringbootmybatis.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> userList() {
        List<User> userList = userMapper.userList();
        return userList;
    }
}
