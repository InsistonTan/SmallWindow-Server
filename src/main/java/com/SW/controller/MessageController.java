package com.SW.controller;

import com.SW.domain.Message;
import com.SW.service.MessageService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.type.SimpleTypeRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private HttpServletRequest request;

    //获取登陆的点赞的帖子
    @RequestMapping(value = "/api/getMyLikeMsg")
    @ResponseBody
    public List<Message> getLikeMsg()
    {
        String login_uid=String.valueOf(request.getSession().getAttribute("UID"));
        return messageService.getLikeMsg(login_uid);
    }

    //获取登陆的收藏的帖子
    @RequestMapping(value = "/api/getMyCollectMsg")
    @ResponseBody
    public List<Message> getCollectMsg()
    {
        String login_uid=String.valueOf(request.getSession().getAttribute("UID"));
        return messageService.getCollectMsg(login_uid);
    }

    //获取top10的帖子
    @RequestMapping(value = "/api/getMsgTop10")
    @ResponseBody
    public List<Message> getTop10()
    {
        return messageService.getTop10();
    }

    //通过index 获取此贴子
    @RequestMapping(value = "/api/getOneMessage")
    @ResponseBody
    public Message getOneMessage(@RequestParam("index") int index)
    {
        return messageService.getOneByIndex(index);
    }

    //获取关注的用户的帖子
    @RequestMapping(value = "/api/getFollowMessage")
    @ResponseBody
    public List<Message> getFollowMessages()
    {
        String uid= String.valueOf(request.getSession().getAttribute("UID"));
        List<Message> result=messageService.getFollowMessage(uid);
        return result;
    }

    //获取自己的所有帖子
    @RequestMapping(value = "/api/getMyMessage")
    @ResponseBody
    public List<Message> getMyMessage()
    {
        String uid= String.valueOf(request.getSession().getAttribute("UID"));
        return messageService.getAllByUID(uid);
    }

    //获取10条最新的帖子
    @RequestMapping(value = "/api/getNewMessage")
    @ResponseBody
    public List<Message> getNewMessages()
    {
        List<Message> result=messageService.getNewMessage();
        return result;
    }
    //修改帖子内容
    @RequestMapping(value ="/api/updateMessage")
    @ResponseBody
    public String updateMessage(@RequestBody Message message)
    {
        String uid = String.valueOf(request.getSession().getAttribute("UID"));
        if(uid==null)
            return "failed";
        message.setUid(uid);
        String result=messageService.updateMessage(message);
        return result;
    }
    //插入新的帖子
    @RequestMapping(value = "/api/insertMessage")
    @ResponseBody
    public String insertMessage(@RequestBody Message message)
    {
        String uid = String.valueOf(request.getSession().getAttribute("UID"));
        String name = String.valueOf(request.getSession().getAttribute("Username"));
        if(uid==null)
            return "failed";
        message.setUid(uid);
        message.setUser(name);
        String result=messageService.insertMessage(message);
        return result;
    }
    //删除帖子
    @RequestMapping(value = "/api/deleteMessage")
    @ResponseBody
    public String deleteMessage(@RequestBody Message delete_message)
    {
        String login_uid=String.valueOf(request.getSession().getAttribute("UID"));
        delete_message.setUid(login_uid);
        return messageService.deleteMessage(delete_message);
    }


}
