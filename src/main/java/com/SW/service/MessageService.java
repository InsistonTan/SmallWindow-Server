package com.SW.service;

import com.SW.dao.*;
import com.SW.domain.Like;
import com.SW.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private ViewDao viewDao;
    @Autowired
    private LikeDao likeDao;
    @Autowired
    private CommentDao commentDao;
    @Autowired
    private CollectDao collectDao;
    @Autowired
    private HttpServletRequest request;

    //获取uid的点赞的帖子
    public List<Message> getCollectMsg(String uid)
    {
        return getView(messageDao.selectCollectMsg(uid));
    }

    //获取uid的点赞的帖子
    public List<Message> getLikeMsg(String uid)
    {
        return getView(messageDao.selectLikeMsg(uid));
    }

    //获取top10的帖子
    public List<Message> getTop10()
    {
        return getView(messageDao.selectTop10());
    }

    //通过index获取此帖子
    public Message getOneByIndex(int index)
    {
        return getOneView(messageDao.selectOneByindex(index));
    }

    //获取一个帖子的浏览，点赞，收藏数
    private Message getOneView(Message message)
    {
        //获取登陆的uid
        String uid=String.valueOf(request.getSession().getAttribute("UID"));
        if(uid!=null&&!("".equals(uid)))
        {
            //判断是否已经点赞
            if(likeDao.searchLike(uid,message.getIndex())!=null)
                message.setLiked(1);
            //判断是否已经收藏
            if(collectDao.searchCollect(uid,message.getIndex())!=null)
                message.setCollected(1);
        }
        //获取浏览数
        int view_num=viewDao.selectViewSumbyIndex(message.getIndex());
        message.setView(view_num);
        //获取点赞数
        int like_num=likeDao.getLikeSum(message.getIndex());
        message.setLike(like_num);
        //获取评论数
        int comment_num=commentDao.getCommentSum(message.getIndex());
        message.setComment(comment_num);
        //获取收藏数
        int collect_num=collectDao.getCollectSum(message.getIndex());
        message.setCollect(collect_num);
        return message;
    }

    //获取每个帖子的浏览，点赞，收藏数
    private List<Message> getView(List<Message> list)
    {
        //获取登陆的uid
        String uid=String.valueOf(request.getSession().getAttribute("UID"));
        //
        int len=list.size();
        for(int i=0;i<len;i++)
        {
            if(uid!=null&&!("".equals(uid)))
            {
                //判断是否已经点赞
                if(likeDao.searchLike(uid,list.get(i).getIndex())!=null)
                    list.get(i).setLiked(1);
                //判断是否已经收藏
                if(collectDao.searchCollect(uid,list.get(i).getIndex())!=null)
                    list.get(i).setCollected(1);
            }

            //获取浏览数
            int view_num=viewDao.selectViewSumbyIndex(list.get(i).getIndex());
            list.get(i).setView(view_num);
            //获取点赞数
            int like_num=likeDao.getLikeSum(list.get(i).getIndex());
            list.get(i).setLike(like_num);
            //获取评论数
            int comment_num=commentDao.getCommentSum(list.get(i).getIndex());
            list.get(i).setComment(comment_num);
            //获取收藏数
            int collect_num=collectDao.getCollectSum(list.get(i).getIndex());
            list.get(i).setCollect(collect_num);

        }
        return list;
    }

    //获取uid关注的用户的帖子
    public List<Message> getFollowMessage(String uid)
    {
        //返回设置了浏览数之后的帖子
        return getView(messageDao.selectFollowMessage(uid));
    }

    //获取uid的所有帖子
    public List<Message> getAllByUID(String uid)
    {
        return getView(messageDao.selectAllByUID(uid));
    }

    //获取10条最新的帖子
    public List<Message> getNewMessage()
    {
        return getView(messageDao.selectNewMessage());
    }

    //插入帖子
    public String insertMessage(Message message)
    {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=dateFormat.format(new Date());
        message.setTime(time);
        if(messageDao.insertMessage(message))
            return "success";
        else
            return "failed";
    }

    //修改帖子内容
    public String updateMessage(Message message)
    {
        if(messageDao.updateMessage(message))
            return "success";
        else return "failed";
    }

    //删除帖子
    public String deleteMessage(Message message)
    {
        if(messageDao.deleteMessage(message))
            return "success";
        else return "failed";
    }

    //检查当前登陆的uid与要删除的帖子的uid是否匹配
    public String checkUID(Message message,String uid)
    {
        String temp_uid=messageDao.selectUid(message);
        if(temp_uid.equals(uid))
            return "match";
        else
            return "not match";
    }
}
