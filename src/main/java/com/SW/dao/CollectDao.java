package com.SW.dao;

import com.SW.domain.Collect;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface CollectDao {

    //通过uid和msg_index来查找记录
    @Select("select * from collects where msg_index=#{index} and uid=#{uid}")
    Collect searchCollect(@Param("uid") String uid,@Param("index") int msg_index);

    //增加收藏
    @Insert("insert into collects(uid,msg_index,time) values(#{uid},#{msg_index},#{time})")
    boolean addCollect(Collect collect);

    //取消收藏
    @Delete("delete from collects where msg_index=#{msg_index} and uid=#{uid}")
    boolean cancelCollect(Collect collect);

    //获取某个帖子收藏数
    @Select("select count(id) from collects where msg_index=#{index}")
    int getCollectSum(@Param("index") int msg_index);
}
