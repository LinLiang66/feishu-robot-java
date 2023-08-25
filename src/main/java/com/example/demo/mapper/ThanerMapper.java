package com.example.demo.mapper;

import com.example.demo.entity.Thaner;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ThanerMapper {

    @Select("select * from thaner")
    public List<Thaner> getthaner();

}
