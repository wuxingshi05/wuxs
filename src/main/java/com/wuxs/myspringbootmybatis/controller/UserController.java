package com.wuxs.myspringbootmybatis.controller;

import com.wuxs.myspringbootmybatis.form.RegisteredForm;
import com.wuxs.myspringbootmybatis.service.UserService;
import com.wuxs.myspringbootmybatis.util.EmailUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

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
    @RequestMapping(value = "/registered",method = RequestMethod.GET)
    public ModelAndView registered(RegisteredForm registeredForm){
        int result = userService.insertUser(registeredForm);
        if (result == 1){
            return new ModelAndView("redirect:login.html");
        }
        return new ModelAndView("redirect:registered.html");
    }

    /**
     * 跳转Code
     * @return
     */
    @RequestMapping(value = "/code",method = RequestMethod.GET)
    public ModelAndView code(){
        return new ModelAndView("test");
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
    public int getCode(String addto){
        int result = emailUtil.sendSimpleEmail(addto);
        return result;
    }

    /**
     *
     * @param response
     * @throws IOException
     */
    @RequestMapping(value="/random",method = RequestMethod.GET)
    public void findRandom (HttpServletResponse response) throws IOException {
        // 验证码字符个数
        int codeCount = 4;
        char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',
                'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',
                'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };

        // 创建一个随机数生成器类
        Random random = new Random();
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String strRand = String.valueOf(codeSequence[random.nextInt(36)]);
            // 将产生的四个随机数组合在一起。
            randomCode.append(strRand);
        }
        PrintWriter out = response.getWriter();
        out.print(randomCode);
    }

}
