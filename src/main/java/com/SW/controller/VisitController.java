package com.SW.controller;
import com.SW.dao.FollowDao;
import com.SW.dao.UserDao;
import com.SW.domain.Follow;
import com.SW.domain.Message;
import com.SW.domain.User;
import com.SW.service.FollowService;
import com.SW.service.MessageService;
import com.SW.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class VisitController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private UserService userService;
    @Autowired
    private MessageService messageService;
    @Autowired
    private FollowService followService;
    @Autowired
    private FollowDao followDao;

    //保存要浏览他人主页，他人的UID
    @RequestMapping(value = "/api/visitOtherHome")
    @ResponseBody
    public String visitOtherHome(@RequestParam(value="visitUID") String visitUID)
    {
        System.out.println("保存受访问者uid："+visitUID);
        if("".equals(visitUID)||visitUID==null)
        {
            return "failed";
        }
        else
        {
            request.getSession().setAttribute("visitUID",visitUID);
            return "success";
        }

    }

    //获取他人的个人信息
    @RequestMapping(value = "/api/getVisitUserInfo")
    @ResponseBody
    public User getVisitUserInfo(String visitUID)
    {
        //String visitUID=String.valueOf(request.getSession().getAttribute("visitUID"));
        User result=userService.getUserInfo(visitUID);
        //判断是否已经关注
        String myUID=String.valueOf(request.getSession().getAttribute("UID"));
        Follow temp=new Follow();
        temp.setMyUID(myUID);
        temp.setTargetUID(visitUID);
        if(followDao.selectOne(temp)!=null)
            result.setIsFollowed(1);
        return result;
    }

    //获取他人的帖子
    @RequestMapping(value = "/api/getVisitMessage")
    @ResponseBody
    public List<Message> getVisitMessage(String visitUID)
    {
        //String uid=String.valueOf(request.getSession().getAttribute("visitUID"));
        return messageService.getAllByUID(visitUID);
    }

    //获取他人的关注
    @RequestMapping(value = "/api/getVisitFollow")
    @ResponseBody
    public List<User> getVisitFollow(String visitUID)
    {
        return followService.getAllFollow(visitUID);
    }

    //获取他人的粉丝
    @RequestMapping(value = "/api/getVisitFan")
    @ResponseBody
    public List<User> getVisitFan(String visitUID)
    {
        return followService.getAllFan(visitUID);
    }
}
