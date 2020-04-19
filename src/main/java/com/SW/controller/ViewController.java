package com.SW.controller;
import com.SW.domain.View;
import com.SW.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ViewController {
    @Autowired
    private ViewService viewService;

    //增加浏览记录
    @RequestMapping(value = "/api/addView")
    @ResponseBody
    public String addView(@RequestBody View view)
    {
        return viewService.addView(view);
    }

}
