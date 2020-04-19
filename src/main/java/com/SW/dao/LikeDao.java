package com.SW.dao;

import com.SW.domain.Like;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface LikeDao {

    //通过uid和msg_index来查找记录
    @Select("select * from likes where msg_index=#{index} and uid=#{uid}")
    Like searchLike(@Param("uid") String uid,@Param("index") int msg_index);

    //增加like记录
    @Insert("insert into likes(uid,msg_index,time) values(#{uid},#{msg_index},#{time})")
    boolean addLike(Like like);

    //取消like
    @Delete("delete from likes where msg_index=#{msg_index} and uid=#{uid}")
    boolean cancelLike(Like like);

    //获取某个帖子的点赞数
    @Select("select count(id) from likes where msg_index=#{index}")
    int getLikeSum(@Param("index") int msg_index);
}
