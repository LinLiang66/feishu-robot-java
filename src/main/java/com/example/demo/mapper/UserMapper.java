package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user")
    public List<User> getus(User user);


    @Select("select * from user where username=#{username} and password=#{password}")
    public List<User> getuser(User user);

    @Insert("INSERT INTO user (username,password)VALUES (#{username},#{password})")
    public void insertuser(User user);

    @Update("update  user set   username=#{username},password=#{password}  where id=#{id}")
    public void gengxin(User user);

    @Delete("delete from user where id=#{id}")
    public void shanchu(User user);
}
