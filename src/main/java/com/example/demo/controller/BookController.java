package com.example.demo.controller;

import com.example.demo.config.ErrorResolve.FrontResult;
import com.example.demo.config.ErrorResolve.ResultCodeEnum;
import com.example.demo.config.ErrorResolve.ResutlMsgEnum;
import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.mapper.BookMapper;
import com.example.demo.service.BookService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
@Slf4j
@Api(value = "/file", tags = "查询管理")
public class BookController {

    @Autowired
    BookService bookService;

    @Autowired
    BookMapper bookMapper;

    @PostMapping("/getbook")
    public FrontResult getbook(@RequestBody Book book) {

        return FrontResult.build(ResultCodeEnum.SUCCESS.getCode(),
                ResutlMsgEnum.EXECUTE_SUCCESS.getMsg(), bookService.getbook(book));
    }

    @GetMapping("/getgetbook")
    public FrontResult getgetbook(@RequestParam(value = "listid") String listid) {

        return FrontResult.build(ResultCodeEnum.SUCCESS.getCode(),
                ResutlMsgEnum.EXECUTE_SUCCESS.getMsg(), bookService.getgetbook(listid));
    }

//    @PostMapping("/gxgetbook")
//    public FrontResult gxgetbook(@RequestBody Book book) {
//        bookMapper.gengxinbook(book);
//        return FrontResult.build(ResultCodeEnum.SUCCESS.getCode(),
//                ResutlMsgEnum.EXECUTE_SUCCESS.getMsg(), "修改成功");
//    }


    @PostMapping("/insertbook")
    public FrontResult insertbook(@RequestBody Book book) {

        return FrontResult.build(ResultCodeEnum.SUCCESS.getCode(),
                ResutlMsgEnum.EXECUTE_SUCCESS.getMsg(), bookService.insertbook(book));
    }

//
//    @PostMapping("/shanchubook")
//    public FrontResult shanchubook(@RequestBody Book book) {
//
//        return FrontResult.build(ResultCodeEnum.SUCCESS.getCode(),
//                ResutlMsgEnum.EXECUTE_SUCCESS.getMsg(), bookService.shanchu(book) );
//    }

}
