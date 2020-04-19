package com.SW.service;

import com.SW.dao.FollowDao;
import com.SW.dao.MessageDao;
import com.SW.dao.UserDao;
import com.SW.domain.User;
import com.SW.domain.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private FollowDao followDao;

    //获取用户的信息
    public User getUserInfo(String uid)
    {
        User result=userDao.getUserInfo(uid);
        if(result==null)
        {
            System.out.println("UserService_getUserInfo的result为空");
            return new User();
        }

        int followNUM=followDao.selectFollowsNum(uid);
        int fanNUM=followDao.selectFansNum(uid);
        int messageNUM=messageDao.selectMessageNum(uid);
        result.setFollowNum(followNUM);
        result.setFanNum(fanNUM);
        result.setMessageNum(messageNUM);
        return result;
    }
    //登陆
    public String loginCheck(User loginUser)
    {
        User temp=userDao.selectUser(loginUser.getUsername());
        if(temp==null)
            return "user not exist";//用户不存在
        else if(temp.getPassword().equals(loginUser.getPassword()))
            return "success";//登录成功
        else return "password incorrect";//密码错误
    }
    //注册
    public String registerUser(User registerUser)
    {
        int UID=0;
        //循环产生10001-20001的UID
        Random random=new Random();
        for(int i=0;i<10000;i++)
        {
            int randomID=random.nextInt(10000)+10001;//随机产生用户ID
            User u=userDao.selectUser(Integer.toString(randomID));
            if(u==null)
            {
                UID=randomID;
                registerUser.setUID(Integer.toString(UID));
                break;
            }

        }
        //未能产生正确的UID
        if(UID==0)
            return "failed";
        //注册时间
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
        String time=dateFormat.format(new Date());
        registerUser.setRegisterTime(time);
        //检查此用户名是否已存在
        User temp=userDao.selectUser(registerUser.getUsername());
        if(temp==null)
        {
            if(userDao.insertUser(registerUser))
            {
                return "success";//注册成功
            }
            else return "failed";//注册失败

        }
        else return "user exist";//用户已存在，注册失败
    }
    public String getUIDbyName(User loginUser)
    {
        String result=userDao.selectUID(loginUser.getUsername());
        return result;
    }
    //修改简介信息
    public String updateUser(User user)
    {
        if(userDao.updateUser(user))
            return "success";
        else return "false";
    }
}
