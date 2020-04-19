package com.SW.controller;
import com.SW.domain.Collect;
import com.SW.service.CollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CollectController {
    @Autowired
    private CollectService collectService;
    @Autowired
    private HttpServletRequest request;

    //增加收藏
    @RequestMapping(value = "/api/addCollect")
    @ResponseBody
    public String addCollect(@RequestBody Collect collect)
    {
        return collectService.addCollect(collect);
    }

    //取消收藏
    @RequestMapping(value = "/api/cancelCollect")
    @ResponseBody
    public String cancelCollect(@RequestBody Collect collect)
    {
        String uid=String.valueOf(request.getSession().getAttribute("UID"));
        if (uid==null||"".equals(uid))
            return "failed";
        else
        {
            collect.setUid(uid);
            return collectService.cancelCollect(collect);
        }
    }
}
