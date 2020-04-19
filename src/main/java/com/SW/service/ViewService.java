package com.SW.service;
import com.SW.dao.ViewDao;
import com.SW.domain.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class ViewService {
    @Autowired
    private ViewDao viewDao;

    //增加浏览记录
    public String addView(View view)
    {
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=dateFormat.format(new Date());
        view.setTime(time);
        if(viewDao.insertView(view))
            return "success";
        else return "failed";
    }
//    //获取某个帖子的浏览总数
//    public int getViewSumByindex(View view)
//    {
//        return viewDao.selectViewSumbyIndex(view);
//    }
}
