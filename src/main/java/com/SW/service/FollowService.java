package com.SW.service;
import com.SW.dao.FollowDao;
import com.SW.dao.MessageDao;
import com.SW.domain.Follow;
import com.SW.domain.User;
import com.SW.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class FollowService {
    @Autowired
    private FollowDao followDao;
    @Autowired
    HttpServletRequest request;

    //查询此UID关注的所有元组
    public List<User> getAllFollow(String uid)
    {
        List<User> result=followDao.selectAllFollows(uid);
        String login_uid=String.valueOf(request.getSession().getAttribute("UID"));
        //判断是否是查询登录者的关注，则全是已关注
        if(login_uid==uid)
        {
            for(int i=0;i<result.size();i++)
            {
                result.get(i).setIsFollowed(1);
            }
        }
        //非登陆者的关注，则需要判断
        else
        {
            //判断是否登录，是则检查
            if(login_uid!=null&&"".equals(login_uid))
            for(int i=0;i<result.size();i++)
            {
                //判断是否已经关注
                Follow temp=new Follow();
                temp.setMyUID(login_uid);
                temp.setTargetUID(result.get(i).getUID());
                if(followDao.selectOne(temp)!=null)
                    result.get(i).setIsFollowed(1);
            }
        }

        return result;
    }
    //查询此uid的所有粉丝
    public List<User> getAllFan(String uid)
    {
        List<User> result= followDao.selectAllFans(uid);
        String login_uid=String.valueOf(request.getSession().getAttribute("UID"));
        //是否登录
        if(login_uid!=null&&"".equals(login_uid))
        for(int i=0;i<result.size();i++)
        {
            //判断是否已经关注
            Follow temp=new Follow();
            temp.setMyUID(login_uid);
            temp.setTargetUID(result.get(i).getUID());
            if(followDao.selectOne(temp)!=null)
                result.get(i).setIsFollowed(1);
        }
        return result;
    }
    //增加关注元组
    public String addFollow(Follow follow)
    {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String time=dateFormat.format(new Date());
        follow.setTime(time);
        //
        Follow temp=followDao.selectOne(follow);
        if(temp==null)
        {
            if(followDao.insertFollow(follow))
                return "success";
            else
                return "failed";
        }
        else
            return "already followed";
    }
    //取消关注
    public String cancelFollow(Follow follow)
    {
        if(followDao.deleteFollow(follow))
            return "success";
        else
            return "failed";
    }
}
