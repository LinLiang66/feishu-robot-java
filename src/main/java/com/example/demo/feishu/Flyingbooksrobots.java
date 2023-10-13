package com.example.demo.feishu;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.FeishuParams;
import com.example.demo.xunfei.XunfeiSocketHandler;
import com.example.demo.xunfei.XunfeisendMsg;
import com.lark.oapi.core.exception.DecryptException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Component
@RestController
@Slf4j
public class Flyingbooksrobots {
    public void FlyingbooksrobotV2(FeishuParams.Flyingbookevent flyingbookevent) throws Exception {
        String Message = flyingbookevent.getEvent().getMessage().getContent();
        String MessageType = flyingbookevent.getEvent().getMessage().getMessageType();
        FeishuParams.Content content = JSONObject.parseObject(Message, FeishuParams.Content.class);
        String App_id = flyingbookevent.getHeader().getAppid();
            Pattern pattern = Pattern.compile("小肉");
            //这里对消息内容进行处理，默认包含小肉字样的为向机器人提问
            Matcher matcher = pattern.matcher(Message);
            if (matcher.find()) {
                StringBuilder Texts = new StringBuilder();
                if ("text".equals(MessageType)) {
                    Texts = new StringBuilder(JSONObject.parseObject(Message, FeishuParams.Contentdetails.class).getText());
                } else {
                    for (List<FeishuParams.Contentdetails> Contents : content.getContent()) {
                        for (FeishuParams.Contentdetails Contentss : Contents) {
                            if ("text".equals(Contentss.getTag())) {
                                Texts.append(Contentss.getText());
                            }
                        }
                    }
                }
                XunfeisendMsg xunfeisendMsg = new XunfeisendMsg();
                xunfeisendMsg.setUserId(App_id);
                xunfeisendMsg.setContent(Texts.toString().replaceAll("小肉", ""));
                xunfeisendMsg.setFeishumsg(flyingbookevent);
                XunfeiSocketHandler.send(xunfeisendMsg);

            }

    }

    /**
     * key Sting转byte[]
     */
    public byte[] Decryptor(String keyStr) {
        MessageDigest digest = null;

        try {
            digest = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException var4) {
        }

        if (digest != null) {
            return digest.digest(keyStr.getBytes(StandardCharsets.UTF_8));
        }
        return new byte[0];
    }

    /**
     * Base64内容解密
     */
    public String decrypt(String base64, String key) throws DecryptException {
        try {
            byte[] decode = Base64.getDecoder().decode(base64);
            Cipher cipher = Cipher.getInstance("AES/CBC/NOPADDING");
            byte[] iv = new byte[16];
            System.arraycopy(decode, 0, iv, 0, 16);
            byte[] data = new byte[decode.length - 16];
            System.arraycopy(decode, 16, data, 0, data.length);
            cipher.init(2, new SecretKeySpec(Decryptor(key), "AES"), new IvParameterSpec(iv));
            byte[] r = cipher.doFinal(data);
            if (r.length > 0) {
                int p;
                for (p = r.length - 1; p >= 0 && r[p] <= 16; --p) {
                }

                if (p != r.length - 1) {
                    byte[] rr = new byte[p + 1];
                    System.arraycopy(r, 0, rr, 0, p + 1);
                    r = rr;
                }
            }

            return new String(r, StandardCharsets.UTF_8);
        } catch (Throwable var9) {
            throw new DecryptException(var9);
        }
    }
}