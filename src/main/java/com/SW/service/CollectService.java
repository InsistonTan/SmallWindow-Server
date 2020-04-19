package com.SW.service;

import com.SW.dao.CollectDao;
import com.SW.domain.Collect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class CollectService {
    @Autowired
    private CollectDao collectDao;

    //增加收藏
    public String addCollect(Collect collect)
    {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=dateFormat.format(new Date());
        collect.setTime(time);
        if(collectDao.addCollect(collect))
            return "success";
        else return "failed";
    }

    //取消收藏
    public String cancelCollect(Collect collect)
    {
        if(collectDao.cancelCollect(collect))
            return "success";
        else return "failed";
    }
}
