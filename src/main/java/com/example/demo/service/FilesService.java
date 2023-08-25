package com.example.demo.service;

import com.example.demo.entity.Files;
import com.example.demo.entity.Thaner;
import com.example.demo.mapper.FilesMapper;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.List;

@Service
public class FilesService {

    @Autowired
    FilesMapper filesMapper;

    public List<Files> getmessage(){
        return  filesMapper.getfiles_1();
    }

    public String  insertmess(Files files){
        filesMapper.insertfile(files);
        return  "添加成功";
    }
}
