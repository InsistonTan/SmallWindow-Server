package com.SW.dao;
import com.SW.domain.Follow;
import com.SW.domain.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowDao {

    //选择myUID与targetUID的元组
    @Select("SELECT * FROM follows WHERE myUID=#{myUID} AND targetUID=#{targetUID}")
    Follow selectOne(Follow follow);

    //查询此UID关注的所有元组
    @Select("SELECT Username,UID FROM users,follows WHERE myUID=#{uid} AND UID=targetUID order by time desc")
    List<User> selectAllFollows(@Param("uid") String uid);

    //查询此UID关注数
    @Select("SELECT COUNT(Username) FROM users,follows WHERE myUID=#{uid} AND UID=targetUID")
    int selectFollowsNum(@Param("uid") String uid);

    //查询此uid的所有粉丝
    @Select("SELECT Username,UID FROM users,follows WHERE targetUID=#{uid} AND UID=myUID order by time desc")
    List<User> selectAllFans(@Param("uid") String uid);

    //查询此uid粉丝数
    @Select("SELECT COUNT(Username) FROM users,follows WHERE targetUID=#{uid} AND UID=myUID")
    int selectFansNum(@Param("uid") String uid);

    //增加关注元组
    @Insert("INSERT INTO follows(myUID,targetUID,time) VALUES(#{myUID},#{targetUID},#{time})")
    boolean insertFollow(Follow follow);

    //删除元组
    @Delete("DELETE FROM follows WHERE myUID=#{myUID} AND targetUID=#{targetUID}")
    boolean deleteFollow(Follow follow);
}
