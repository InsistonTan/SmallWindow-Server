package com.SW.service;

import com.SW.dao.LikeDao;
import com.SW.domain.Like;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class LikeService {
    @Autowired
    private LikeDao likeDao;

    //为某个帖子点赞
    public String addLike(Like like)
    {
        //点赞时间
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=dateFormat.format(new Date());
        like.setTime(time);

        if(likeDao.addLike(like))
            return "success";
        else return "failed";
    }

    //取消点赞
    public String cancelLike(Like like)
    {
        if(likeDao.cancelLike(like))
            return "success";
        else return "failed";
    }
}
