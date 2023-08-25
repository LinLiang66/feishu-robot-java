package com.example.demo.service;

import com.example.demo.entity.Thaner;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public List<User> getuser( User user){
        return   userMapper.getuser(user);
    }



    public String insertuser( User user){
        userMapper.insertuser(user);

        return "添加成功";
    }

    public String upd( User user){
        userMapper.gengxin(user);

        return "添加成功";
    }
}
