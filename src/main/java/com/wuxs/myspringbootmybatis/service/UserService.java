package com.wuxs.myspringbootmybatis.service;

import com.wuxs.myspringbootmybatis.entity.User;
import com.wuxs.myspringbootmybatis.form.RegisteredForm;
import com.wuxs.myspringbootmybatis.mapper.UserMapper;
import com.wuxs.myspringbootmybatis.util.MyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.time.LocalDateTime;


@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    public int userCheck(String account, String password) {
        int flag = userMapper.userCheck(account, password);
        return flag;
    }

    /**
     * 注册
     * @param registeredForm
     * @return
     */
    public int insertUser(RegisteredForm registeredForm){

        User user = new User();

        user.setId(MyUtil.getUUID32());
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

    /**
     * 注册信息Check
     * @return
     */
    public String resCheck(RegisteredForm registeredForm){
        String message = "注册成功";
        if (StringUtils.isEmpty(registeredForm.getAccount())){
            message = "账号不能为空！";
        }if (StringUtils.isEmpty(registeredForm.getPassword())){
            message = "密码不能为空！";
        }if (StringUtils.isEmpty(registeredForm.getMsg()) || !registeredForm.getMsg().equals(registeredForm.getCode())){
            message = "验证码不匹配！";
        }
        return message;
    }
}
