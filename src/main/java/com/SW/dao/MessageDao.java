package com.SW.dao;

import com.SW.domain.Message;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao {
    //获取此uid的收藏的帖子
    @Select("select messages.* from messages,collects where collects.uid=#{uid} " +
            "and messages.`index`=collects.msg_index order by collects.time desc")
    List<Message> selectCollectMsg(@Param("uid")String uid);

    //获取此uid的点赞过的帖子
    @Select("select messages.* from messages,likes where likes.uid=#{uid} " +
            "and messages.`index`=likes.msg_index order by likes.time desc")
    List<Message> selectLikeMsg(@Param("uid")String uid);

    //获取浏览数前十的帖子
    @Select("select messages.* from messages,views " +
            "where `index` = msg_index GROUP BY msg_index ORDER BY COUNT(id) DESC limit 10")
    List<Message> selectTop10();

    //通过index选择一个帖子
    @Select("select * from messages where `index`=#{index}")
    Message selectOneByindex(@Param("index")int index);

    //选择此index的帖子的发帖人uid
    @Select("select uid from messages where `index` = #{index}")
    String selectUid(Message message);

    //选择此uid关注的用户发的帖子
    @Select("select * from messages where uid in " +
            "(select targetUID from follows where myUID=#{uid}) " +
            "or uid=#{uid} order by time desc")
    List<Message> selectFollowMessage(@Param("uid")String uid);

    //选择此uid所发的所有帖子
    @Select("select * from messages where uid=#{uid} order by time desc")
    List<Message> selectAllByUID(@Param("uid") String UID);

    //选择此uid所发帖子数
    @Select("select count(*) from messages where uid=#{uid}")
    int selectMessageNum(@Param("uid") String UID);

    //选择最新的10个帖子
    @Select("select * from messages order by time desc limit 10")
    List<Message> selectNewMessage();

    //插入帖子
    @Insert("insert into messages(uid,user,content,time) values(#{uid},#{user},#{content},#{time})")
    boolean insertMessage(Message message);

    //修改帖子内容
    @Update("update messages set content=#{content} where `index`=#{index} and uid=#{uid}")
    boolean updateMessage(Message message);

    //删除某个帖子
    @Delete("delete from messages where `index` = #{index} and uid=#{uid}")
    boolean deleteMessage(Message message);
}
