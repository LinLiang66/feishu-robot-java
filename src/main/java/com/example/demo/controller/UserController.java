//package com.example.demo.controller;
//
//
//import com.example.demo.config.ErrorResolve.FrontResult;
//import com.example.demo.config.ErrorResolve.ResultCodeEnum;
//import com.example.demo.config.ErrorResolve.ResutlMsgEnum;
//import com.example.demo.entity.User;
//import com.example.demo.mapper.UserMapper;
//import com.example.demo.service.UserService;
//import io.swagger.annotations.Api;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/login")
//@Slf4j
//@Api(value = "/file", tags = "登录")
//public class UserController {
//    @Autowired
//    UserService userService;
//
//    @Autowired
//    UserMapper userMapper;
//
//    @PostMapping("/getp")
//    public FrontResult gettu(@RequestBody User user) {
//
//        return FrontResult.build(ResultCodeEnum.SUCCESS.getCode(),
//                ResutlMsgEnum.EXECUTE_SUCCESS.getMsg(),userMapper.getus(user) );
//    }
//
//
//    @PostMapping("/getu")
//    public FrontResult getu(@RequestBody User user) {
//
//        return FrontResult.build(ResultCodeEnum.SUCCESS.getCode(),
//                ResutlMsgEnum.EXECUTE_SUCCESS.getMsg(), userService.getuser(user) );
//    }
//
//
//    @PostMapping("/insertuser")
//    public FrontResult insertuser(@RequestBody User user) {
//
//        return FrontResult.build(ResultCodeEnum.SUCCESS.getCode(),
//                ResutlMsgEnum.EXECUTE_SUCCESS.getMsg(), userService.insertuser(user) );
//    }
//
//    @PostMapping("/upd")
//    public FrontResult upd(@RequestBody User user) {
//
//        return FrontResult.build(ResultCodeEnum.SUCCESS.getCode(),
//                ResutlMsgEnum.EXECUTE_SUCCESS.getMsg(), userService.upd(user) );
//    }
//
//    @PostMapping("/shanc")
//    public FrontResult shanc(@RequestBody User user) {
//        userMapper.shanchu(user);
//        return FrontResult.build(ResultCodeEnum.SUCCESS.getCode(),
//                ResutlMsgEnum.EXECUTE_SUCCESS.getMsg(), "删除成功");
//    }
//
//
//
//}
