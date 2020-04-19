package com.SW.controller;
import com.SW.domain.Follow;
import com.SW.domain.User;
import com.SW.domain.UserInfo;
import com.SW.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class FollowController {
    @Autowired
    private FollowService followService;
    @Autowired
    private HttpServletRequest request;

    /*//获取关注、粉丝、帖子数量信息
    @RequestMapping(value = "/Follow/getFollowInfo")
    @ResponseBody
    public User getFollowInfo()
    {
        return followService.getFollowInfo(String.valueOf(request.getSession().getAttribute("UID")));
    }*/

    //获取所有关注者
    @RequestMapping(value = "/api/getAllFollow")
    @ResponseBody
    public List<User> getAllFollow()
    {
        return followService.getAllFollow(String.valueOf(request.getSession().getAttribute("UID")));
    }

    //获取所有粉丝
    @RequestMapping(value = "/api/getAllFan")
    @ResponseBody
    public List<User> getAllFan()
    {
        return followService.getAllFan(String.valueOf(request.getSession().getAttribute("UID")));
    }

    //增加关注
    @RequestMapping(value = "/api/addFollow")
    @ResponseBody
    public String addFollow(@RequestBody Follow follow)
    {
        if(request.getSession().getAttribute("UID")==null)
            return "failed";
        return followService.addFollow(follow);
    }

    //取消关注
    @RequestMapping(value = "/api/cancelFollow")
    @ResponseBody
    public String cancelFollow(@RequestBody Follow follow)
    {
        String login_uid=String.valueOf(request.getSession().getAttribute("UID"));
        if(login_uid==null||"".equals(login_uid))
            return "failed";
        else
        {
            follow.setMyUID(login_uid);
            return followService.cancelFollow(follow);
        }
    }
}
