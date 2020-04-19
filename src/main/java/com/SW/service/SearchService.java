package com.SW.service;
import com.SW.dao.FollowDao;
import com.SW.dao.SearchDao;
import com.SW.domain.Follow;
import com.SW.domain.Message;
import com.SW.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {
    @Autowired
    private SearchDao searchDao;
    @Autowired
    private FollowDao followDao;

    //搜索用户
    public List<User> searchUsers(String uid,String name)
    {
        List<User> result= searchDao.searchUser(name);
        //判断登陆状态，uid为空则未登录，则不用判断关注
        if(uid!=null&&uid!=""&&uid!="null")
        {
            //判断是否已经关注
            for(int i=0;i<result.size();i++)
            {
                Follow temp=new Follow();
                temp.setMyUID(uid);
                temp.setTargetUID(result.get(i).getUID());
                if(followDao.selectOne(temp)!=null)
                    result.get(i).setIsFollowed(1);
            }
        }

        return result;
    }
    //搜索帖子
    public List<Message> searchMessages(String value)
    {
        return searchDao.searchMessage(value);
    }
}
