package com.SW.controller;
import com.SW.domain.User;
import com.SW.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/api/login")
    @ResponseBody
    public String login(@RequestBody User loginUser)
    {
        System.out.println(loginUser.getUsername()+"\t"+loginUser.getPassword());
        String result=userService.loginCheck(loginUser);
        System.out.println(result);
        if("success".equals(result))
        {
            request.getSession().setMaxInactiveInterval(12*60*60);
            request.getSession().setAttribute("Username",loginUser.getUsername());
            request.getSession().setAttribute("UID",userService.getUIDbyName(loginUser));
        }
        return result;
        //System.out.println(loginUser);
        //return "1";
    }

    @RequestMapping(value = "/api/register")
    @ResponseBody
    public String register(@RequestBody User registerUser)
    {
        System.out.println(registerUser.getUsername()+"\t"+registerUser.getPassword());
        String result=userService.registerUser(registerUser);
        System.out.println(result);
        return result;
    }

    //
    @RequestMapping(value = "/api/updateUser")
    @ResponseBody
    public String updateUser(@RequestBody User user)
    {
        String uid=String.valueOf(request.getSession().getAttribute("UID"));
        if(uid==null||uid.equals(""))
            return "failed";
        user.setUID(uid);
        return userService.updateUser(user);
    }

    //获取用户信息
    @RequestMapping(value = "/api/getInfo")
    @ResponseBody
    public User getInfo()
    {
        String uid=String.valueOf(request.getSession().getAttribute("UID"));
        User info=userService.getUserInfo(uid);
        return info;
    }

    //
    @RequestMapping(value = "/api/clearInfo")
    @ResponseBody
    public String clearInfo()
    {
        System.out.println("网页请求clearInfo");
        if(request.getSession().getAttribute("UID")!=null&&request.getSession().getAttribute("Username")!=null)
        {
            request.getSession().removeAttribute("UID");
            request.getSession().removeAttribute("Username");
            request.getSession().removeAttribute("keyword");
            return "clearInfo success";
        }
        else return "clearInfo failed";
    }
    @RequestMapping(value = "/api/hello")
    @ResponseBody
    public String Hello()
    {
        return "hello!";
    }

}
