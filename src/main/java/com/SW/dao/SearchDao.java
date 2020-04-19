package com.SW.dao;
import com.SW.domain.Message;
import com.SW.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SearchDao {
    //搜索用户名
    @Select("SELECT Username,UID FROM users WHERE Username LIKE '%${name}%'")
    List<User> searchUser(@Param("name") String name);

    //搜索帖子
    @Select("SELECT * FROM messages WHERE content LIKE '%${value}%' or user LIKE '%${value}%'")
    List<Message> searchMessage(@Param("value")String value);
}
