package com.SW.controller;
import com.SW.dao.SearchDao;
import com.SW.domain.Message;
import com.SW.domain.User;
import com.SW.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    private SearchService searchService;
    @Autowired
    private HttpServletRequest request;

    //保存搜索的keyword
//    @RequestMapping(value = "/api/search")
//    @ResponseBody
//    public String saveKeyWord(@RequestParam(value="keyword") String keyword)
//    {
//        System.out.println(keyword);
//        if(keyword!=null&&keyword!="")
//        {
//            request.getSession().setAttribute("keyword",keyword);
//            return "success";
//        }
//          else return "failed";
//    }

    //搜索用户
    @RequestMapping(value ="/api/searchUser")
    @ResponseBody
    public List<User> searchUsers(@RequestParam(value="keyword") String keyword)
    {
        String uid= String.valueOf(request.getSession().getAttribute("UID"));
        if(keyword==null)
            return null;
        return searchService.searchUsers(uid,keyword);
    }
    //搜索帖子
    @RequestMapping(value = "/api/searchMessage")
    @ResponseBody
    public List<Message> searchMessage(@RequestParam(value="keyword") String keyword)
    {
        if(keyword==null)
            return null;
        return searchService.searchMessages(keyword);
    }

}
