package com.example.demo.mapper;


import com.example.demo.entity.Files;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FilesMapper {

    @Insert("INSERT INTO files (name,date,emain,mes)VALUES (#{name},#{date},#{emain},#{mes})")
    public void insertfile(Files files);

    @Select("select * from files ")
    public List<Files> getfiles_1();

}
