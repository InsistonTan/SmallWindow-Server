package com.SW.dao;
import com.SW.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
    //获取全部信息，用于登录检查
    @Select("select * from users " +
            "where Username=#{user} or UID=#{user}")
    User selectUser(@Param("user") String user);

    //通过Username获得UID
    @Select("select UID from users where Username=#{user}")
    String selectUID(@Param("user") String user);

    //注册
    @Insert("insert into users(UID,Username,Password,registerTime) " +
            "values(#{UID},#{Username},#{Password},#{registerTime})")
    boolean insertUser(User user);

    //修改简介信息
    @Update("update users set sex=#{sex},age=#{age},introduce=#{introduce} where UID=#{UID}")
    boolean updateUser(User user);

    //获取此uid的信息，除了密码
    @Select("select Username,UID,sex,age,introduce from users where UID=#{uid}")
    User getUserInfo(@Param("uid")String uid);
}
