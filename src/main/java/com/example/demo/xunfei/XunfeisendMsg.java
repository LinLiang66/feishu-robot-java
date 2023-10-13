package com.example.demo.xunfei;


import com.example.demo.entity.FeishuParams;
import lombok.Data;

@Data
public class XunfeisendMsg {
    String content;
    String userId;
    FeishuParams.Flyingbookevent feishumsg;
}
