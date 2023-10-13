package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.FeishuParams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.feishu.Flyingbooksrobots;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@RestController
@RequestMapping("/Flyingbook")
@Slf4j
public class Flyingbookservice {

    @Autowired
    Flyingbooksrobots flyingbooks;

    String VerificationToken = ""; //填写你自己的VerificationToken
    String EncryptKey = "";//填写你自己的EncryptKey




    @PostMapping("/robot")
    public void Flyingbookrobot(@RequestBody FeishuParams.Flyingbookencryptionevent flyingbookReceivemsg, HttpServletResponse response) throws Exception {
        JSONObject result = new JSONObject();
        if ("url_verification".equals(flyingbookReceivemsg.getType())&&!flyingbookReceivemsg.getChallenge().isEmpty()){
            //未加密的请求验证
            result.put("challenge", flyingbookReceivemsg.getChallenge());
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(result.toJSONString());
            out.close();
            log.info("验证飞书URL合法性完成=============>>>>>>>>>");
            return;
        }

        String Flyingbook = flyingbooks.decrypt(flyingbookReceivemsg.getEncrypt(), EncryptKey);
        FeishuParams.Flyingbookevent flyingbookevent = JSONObject.parseObject(Flyingbook, FeishuParams.Flyingbookevent.class);
        if ("url_verification".equals(flyingbookevent.getType())) {
//            加密的请求验证
            if (!flyingbookevent.getChallenge().isEmpty() && !flyingbookevent.getToken().isEmpty() && VerificationToken.equals(flyingbookevent.getToken())) {
                result.put("challenge", flyingbookevent.getChallenge());
                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.println(result.toJSONString());
                out.close();
                log.info("验证飞书URL合法性完成=============>>>>>>>>>");

            } else {
                result.put("msg", "签名校验失败，视为非法请求，拒绝处理");
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                PrintWriter out = response.getWriter();
                out.println(result.toJSONString());
                out.close();
                log.info("验证飞书URL非法请求，拒绝处理=============>>>>>>>>>");
            }
        } else if ("im.message.receive_v1".equals(flyingbookevent.getHeader().getEventType())) {
            result.put("msg", true);
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(result.toJSONString());
            out.close();

            if ((System.currentTimeMillis() - Long.parseLong(flyingbookevent.getEvent().getMessage().getCreateTime()) < 5000)) {
                flyingbooks.FlyingbooksrobotV2(flyingbookevent);
            } else {
                log.info("事件推送已超过5秒，不再处理=============>>>>>>>>>" + (System.currentTimeMillis() - Long.parseLong(flyingbookevent.getEvent().getMessage().getCreateTime())));

            }
        } else {
            result.put("msg", "未被服务端定义的事件类型，拒绝处理，如有疑问请联系应用开发者，谢谢");
            response.setStatus(HttpServletResponse.SC_OK);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.println(result.toJSONString());
            out.close();

        }
    }

}
