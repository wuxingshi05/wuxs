package com.wuxs.myspringbootmybatis.controller;

import com.wuxs.myspringbootmybatis.domain.User;
import com.wuxs.myspringbootmybatis.mapper.UserMapper;
import com.wuxs.myspringbootmybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    /*@Autowired
    private UserService userService;*/
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(value = "/test",method = RequestMethod.GET)
    public List<User> userList(){
        //List<User> userList = userService.userList();
        List<User> userList = userMapper.userList();
        return userList;
    }

    @RequestMapping("/simple")
    public String simpleIndex(ModelMap map) {
        // 加入一个属性，用来在模板中读取
        map.addAttribute("host", "http://blog.csdn.net/hry2015/article/");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "simple/index";
    }
}
