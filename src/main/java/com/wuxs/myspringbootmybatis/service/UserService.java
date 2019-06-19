package com.wuxs.myspringbootmybatis.service;

import com.wuxs.myspringbootmybatis.domain.User;
import com.wuxs.myspringbootmybatis.form.RegisteredForm;
import com.wuxs.myspringbootmybatis.mapper.UserMapper;
import com.wuxs.myspringbootmybatis.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public int userCheck(String account, String password) {
        int flag = userMapper.userCheck(account, password);
        return flag;
    }

    public int insertUser(RegisteredForm registeredForm){

        String uuid = MyUtil.getUUID32();

        User user = new User();

        user.setId(uuid);
        user.setAccount(registeredForm.getAccount());
        user.setPassword(registeredForm.getPassword());
        user.setName(registeredForm.getName());
        user.setSex(registeredForm.getSex());
        user.setAddr(registeredForm.getAddr());
        user.setEmail(registeredForm.getEmail());
        user.setFlag(0);
        user.setCreateTime(LocalDateTime.now().toString());
        user.setUpdateTime(LocalDateTime.now().toString());

        int result = userMapper.insertUser(user);
        return result;

    }
}
