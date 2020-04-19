package com.SW.service;
import com.SW.dao.CommentDao;
import com.SW.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentDao commentDao;

    //获取某个帖子的所有评论
    public List<Comment> getMsgComments(Comment comment)
    {
        return commentDao.getMsgComments(comment);
    }

    //增加一条评论
    public String addComment(Comment comment)
    {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=dateFormat.format(new Date());
        comment.setTime(time);
        if (commentDao.addComment(comment))
            return "success";
        else return "failed";
    }

    //修改评论内容
    public String updateComment(Comment comment)
    {
        if(commentDao.updateComment(comment))
            return "success";
        else return "failed";
    }

    //删除评论
    public String deleteComment(Comment comment)
    {
        if (commentDao.deleteComment(comment))
            return "success";
        else return "failed";
    }
}
