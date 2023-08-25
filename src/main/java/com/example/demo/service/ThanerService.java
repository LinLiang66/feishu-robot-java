package com.example.demo.service;

import com.example.demo.entity.Thaner;
import com.example.demo.mapper.ThanerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThanerService {

    @Autowired
    ThanerMapper thanerMapper;

    public List<Thaner> getthanermapper(){

        return   thanerMapper.getthaner();
    }
}
