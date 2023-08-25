package com.example.demo.mapper;

import com.example.demo.entity.Book;
import org.apache.ibatis.annotations.*;


import java.util.List;

@Mapper
public interface BookMapper {
    @Select("select * from book where listid=#{listid}")
    public List<Book> getbooall(Book book);

    @Select("select * from book where listid=#{listid}")
    public List<Book> getbooaid(String listid);

    @Insert("INSERT INTO book (listid,damagenum,responsibilitynum,itemprice,name,state,committime,finishtime)VALUES (#{listid},#{damagenum},#{responsibilitynum},#{itemprice},#{name},#{state},#{committime},#{finishtime})")
    public void insertbook(Book book);

    @Update("update  book set   name=#{name}   where id=#{id}")
    public void gengxinbook(Book book);

    @Delete("delete from book where id=#{id}")
    public void shanchubook(Book book);
}
