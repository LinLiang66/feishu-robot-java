//package com.example.demo.controller;
//
//import com.example.demo.config.ErrorResolve.FrontResult;
//import com.example.demo.config.ErrorResolve.ResultCodeEnum;
//import com.example.demo.config.ErrorResolve.ResutlMsgEnum;
//import com.example.demo.entity.Files;
//import com.example.demo.entity.User;
//import com.example.demo.service.FilesService;
//import com.example.demo.service.ThanerService;
//import io.swagger.annotations.Api;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@RequestMapping("/getthaners")
//
//@Api(value = "/file", tags = "文件")
//public class FilesController {
//
//    @Autowired
//    FilesService filesService;
//
//    @GetMapping("/gettext")
//    public FrontResult gettext() {
//
//        return FrontResult.build(ResultCodeEnum.SUCCESS.getCode(),
//                ResutlMsgEnum.EXECUTE_SUCCESS.getMsg(), filesService.getmessage());
//    }
//
//    @PostMapping("/insertmess")
//    public FrontResult insertuser(@RequestBody Files files) {
//
//        return FrontResult.build(ResultCodeEnum.SUCCESS.getCode(),
//                ResutlMsgEnum.EXECUTE_SUCCESS.getMsg(), filesService.insertmess(files) );
//    }
//
//}