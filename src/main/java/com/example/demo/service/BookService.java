package com.example.demo.service;

import com.example.demo.entity.Book;
import com.example.demo.entity.User;
import com.example.demo.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    @Autowired
    BookMapper bookMapper;

    public List<Book> getbook(Book book) {
        return bookMapper.getbooall(book);
    }

    public List<Book> getgetbook(String listid) {
        return bookMapper.getbooaid(listid);
    }



    public String insertbook(Book book) {
        bookMapper.insertbook(book);

        return "添加成功";
    }

    public String shanchu(Book book) {
        bookMapper.shanchubook(book);

        return "删除成功";
    }
}
