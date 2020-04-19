package com.SW.dao;
import com.SW.domain.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {

    //获取某个帖子的评论数
    @Select("select count(id) from comments where msg_index=#{index}")
    int getCommentSum(@Param("index") int msg_index);

    //获取某个帖子的所有评论
    @Select("select * from comments where msg_index=#{msg_index} order by time desc")
    List<Comment> getMsgComments(Comment comment);

    //增加一条评论
    @Insert("insert into comments(msg_index,username,uid,content,time) " +
            "values(#{msg_index},#{username},#{uid},#{content},#{time})")
    boolean addComment(Comment comment);

    //修改评论内容
    @Update("update comments set content=#{content} where `id`=#{id} and uid=#{uid}")
    boolean updateComment(Comment comment);

    //删除评论
    @Delete("delete from comments where `id`=#{id} and ( uid=#{uid} " +
            "or uid in (select uid from messages where `index`=#{msg_index}))")
    boolean deleteComment(Comment comment);
}
