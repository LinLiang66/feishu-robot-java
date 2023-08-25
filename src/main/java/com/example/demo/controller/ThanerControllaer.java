//package com.example.demo.controller;
//
//
//import com.example.demo.config.ErrorResolve.FrontResult;
//import com.example.demo.config.ErrorResolve.ResultCodeEnum;
//import com.example.demo.config.ErrorResolve.ResutlMsgEnum;
//import com.example.demo.service.ThanerService;
//import io.swagger.annotations.Api;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//@RestController
//@RequestMapping("/getthaners")
//
//@Api(value = "/file", tags = "获取对比")
//public class ThanerControllaer {
//
//    @Autowired
//    ThanerService thanerService;
//
//    @GetMapping("/getall")
//    public FrontResult getal() {
//
//        return FrontResult.build(ResultCodeEnum.SUCCESS.getCode(),
//                ResutlMsgEnum.EXECUTE_SUCCESS.getMsg(), thanerService.getthanermapper());
//    }
//
//}
