package com.SW.controller;
import com.SW.domain.Comment;
import com.SW.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private HttpServletRequest request;

    //获取某个帖子的所有评论
    @RequestMapping(value = "/api/getMsgComments")
    @ResponseBody
    public List<Comment> getMsgComments(@RequestBody Comment comment)
    {
        return commentService.getMsgComments(comment);
    }

    //增加一条评论
    @RequestMapping(value = "/api/addComment")
    @ResponseBody
    public String addComment(@RequestBody Comment comment)
    {
        String uid=String.valueOf(request.getSession().getAttribute("UID"));
        String username=String.valueOf(request.getSession().getAttribute("Username"));
        if(uid==null||"".equals(uid))
            return "failed";
        else
        {
            comment.setUid(uid);
            comment.setUsername(username);
            return commentService.addComment(comment);
        }
    }

    //修改评论内容
    @RequestMapping(value = "/api/updateComment")
    @ResponseBody
    public String updateComment(@RequestBody Comment comment)
    {
        String uid=String.valueOf(request.getSession().getAttribute("UID"));
        if(uid==null||"".equals(uid))
            return "failed";
        else
        {
            comment.setUid(uid);
            return commentService.updateComment(comment);
        }
    }

    //删除评论
    @RequestMapping(value = "/api/deleteComment")
    @ResponseBody
    public String deleteComment(@RequestBody Comment comment)
    {
        String uid=String.valueOf(request.getSession().getAttribute("UID"));
        if(uid==null||"".equals(uid))
            return "failed";
        else
        {
            comment.setUid(uid);
            return commentService.deleteComment(comment);
        }
    }
}
