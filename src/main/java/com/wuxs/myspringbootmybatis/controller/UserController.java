package com.wuxs.myspringbootmybatis.controller;

import com.wuxs.myspringbootmybatis.domain.User;
import com.wuxs.myspringbootmybatis.form.RegisteredForm;
import com.wuxs.myspringbootmybatis.service.UserService;
import com.wuxs.myspringbootmybatis.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private EmailUtil emailUtil;

    /**
     * 跳转首页面
     * @return
     */
    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public ModelAndView index(){
        return new ModelAndView("index");
    }

    /**
     * 跳转注册页面
     * @return
     */
    @RequestMapping(value = "/res",method = RequestMethod.GET)
    public ModelAndView res(){
        return new ModelAndView("registered");
    }

    /**
     * 跳转更改头像页面
     * @return
     */
    @RequestMapping(value = "/avatar",method = RequestMethod.GET)
    public ModelAndView avatar(){
        return new ModelAndView("avatar");
    }

    /**
     * 注册
     * @param registeredForm
     * @return
     */
    @RequestMapping(value = "/registered",method = RequestMethod.POST)
    public ModelAndView registered(RegisteredForm registeredForm){
        int result = userService.insertUser(registeredForm);
        if (result == 1){
            return new ModelAndView("redirect:login.html");
        }
        return new ModelAndView("redirect:registered.html");
    }

    /**
     * 登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ModelAndView login(HttpServletRequest request){
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        int flag = userService.userCheck(account, password);
        if (flag == 1){
            return new ModelAndView("index");
        }
        return new ModelAndView("login");
    }

    /**
     * 获取验证码
     */
    @RequestMapping(value = "/getCode",method = RequestMethod.GET)
    public String getCode(String addto){
        String result = emailUtil.sendSimpleEmail(addto);
        return result;
    }

}
