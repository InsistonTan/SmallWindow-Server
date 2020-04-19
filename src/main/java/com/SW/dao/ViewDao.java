package com.SW.dao;
import com.SW.domain.View;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewDao {
    //增加浏览记录
    @Insert("insert into views(uid,msg_index,time) values(#{uid},#{msg_index},#{time})")
    boolean insertView(View view);

    //获取某个帖子的浏览总数
    @Select("select count(id) from views where msg_index=#{msg_index}")
    int selectViewSumbyIndex(@Param("msg_index") int index);
}
