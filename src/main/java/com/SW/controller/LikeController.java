package com.SW.controller;
import com.SW.domain.Like;
import com.SW.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LikeController {
    @Autowired
    private LikeService likeService;
    @Autowired
    private HttpServletRequest request;

    //为某个帖子点赞
    @RequestMapping(value = "/api/addLike")
    @ResponseBody
    public String addLike(@RequestBody Like like)
    {
        return likeService.addLike(like);
    }

    //取消点赞
    @RequestMapping(value = "/api/cancelLike")
    @ResponseBody
    public String cancelLike(@RequestBody Like like)
    {
        String uid=String.valueOf(request.getSession().getAttribute("UID"));
        //uid为空则说明未登录
        if (uid==null||"".equals(uid))
            return "failed";
        else
        {
            like.setUid(uid);
            return likeService.cancelLike(like);
        }
    }
}
