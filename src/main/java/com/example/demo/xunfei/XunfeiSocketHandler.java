package com.example.demo.xunfei;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.entity.FeishuParams;
import com.lark.oapi.Client;
import com.lark.oapi.core.request.RequestOptions;
import com.lark.oapi.service.im.v1.model.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author xiayk
 * @date 2023/7/19
 */
@Slf4j
public class XunfeiSocketHandler extends WebSocketListener {

//    星火大模型============================================================================
    public static String hostUrl = "https://spark-api.xf-yun.com/v2.1/chat";//这里用的是2.0模型
    public static String APPID = "";//从讯飞开放平台控制台服务接口认证信息中获取APPID
    public static String APISecret = "";//从讯飞开放平台控制台服务接口认证信息中获取APISecret
    public static String APIKEY = "";//从讯飞开放平台控制台服务接口认证信息中获取APIKey





    //    飞书机器人============================================================================

    private final String userId; //用于区分用户提问

    private static final  String AppSecret="kCI4rVklhpKMRbzHzdoWveauRQUSmom2";//从飞书开放平台获取

    private final FeishuParams.Flyingbookevent feishumsg; //飞书事件详情，用于异步响应、回复和更新卡片消息
    public String question;//提问的内容

    private static String MessageId;//需要更新的卡片消息ID，用于实现打字机效果，流式输出

    private String answer="";//得到的结果

    //根据 APP ID 获取 AppSecret ，多个公用的情况下
    public static String getAppSecret(String SendAppid){
        switch (SendAppid) {
            case "cli_a5acc5f93e79500c":// AI-robot
                return AppSecret;
        }
        return SendAppid;
    }


    public XunfeiSocketHandler(String userId, String content, FeishuParams.Flyingbookevent feishumsg) {
        if (userId == null) {
            this.userId = UUID.randomUUID().toString().replace("-", "");
        } else {
            this.userId = userId;
        }
        this.feishumsg = feishumsg;
        this.question = content;
    }

    public XunfeiSocketHandler(String content, FeishuParams.Flyingbookevent feishumsg) {
        this(UUID.randomUUID().toString().replace("-", ""), content,feishumsg);
    }

    public static void sends(XunfeisendMsg xunfeisendMsg) throws Exception {
        String content = xunfeisendMsg.getContent();
        String userId = xunfeisendMsg.getUserId();
        FeishuParams.Flyingbookevent feishumsg = xunfeisendMsg.getFeishumsg();
        replyCard(feishumsg);
        //构建鉴权httpurl
        String authUrl;
        try {
            authUrl = getAuthorizationUrl(hostUrl, APIKEY, APISecret);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        String url = authUrl.replace("https://", "wss://").replace("http://", "ws://");
        Request request = new Request.Builder().url(url).build();
        XunfeiSocketHandler socketHandler = new XunfeiSocketHandler(userId, content,feishumsg);
        okHttpClient.newWebSocket(request, socketHandler);
    }

    public static void send(XunfeisendMsg xunfeisendMsg) throws Exception {
         sends(xunfeisendMsg);
    }
    // withMainMd 用于生成markdown消息体
    private static FeishuParams.Element withMainMd(String msg) {
        FeishuParams.Element MainMd =new FeishuParams.Element();
        //自动加粗效果
        MainMd.setContent(msg.replaceAll("(?m)^(.*)$", "**$1**"));
        MainMd.setTag("markdown");
        return MainMd;
    }

    // withMainText 用于生成纯文本消息体
    private static FeishuParams.Element withMainText(String msg) {
        FeishuParams.Text msgeText =new FeishuParams.Text();
        FeishuParams.Field msgel =new FeishuParams.Field();
        msgeText.setTag("plain_text");
        msgeText.setContent(msg);
        msgel.setText(msgeText);
        msgel.set_short(false);
        List<FeishuParams.Field> msgelField=new ArrayList<>();
        msgelField.add(msgel);
        FeishuParams.Element MainText =new FeishuParams.Element();
        MainText.setFields(msgelField);
        MainText.setTag("div");
        return MainText;
    }

    // withNote 用于生成纯文本备注
    private static FeishuParams.Element withNote(String note) {
        FeishuParams.Element noteel =new FeishuParams.Element();
        noteel.setContent(note);
        noteel.setTag("plain_text");
        List<FeishuParams.Element> noteels=new ArrayList<>();
        noteels.add(noteel);
        FeishuParams.Element notediv =new FeishuParams.Element();
        notediv.setTag("note");
        notediv.setElements(noteels);
        return notediv;
    }


    public void updateTextCard(String note) throws Exception {
        // 构建TextCard
        FeishuParams.MessageCard MsgCard = getAI_RobotCard(this.answer, note);
        String SendAppid = feishumsg.getHeader().getAppid();
        // 构建client
        Client client = Client.newBuilder(SendAppid, getAppSecret(SendAppid))
                .build();
        // 创建请求对象
        PatchMessageReq req = PatchMessageReq.newBuilder()
                .messageId(MessageId)//回复消息id
                .patchMessageReqBody(PatchMessageReqBody.newBuilder()
                        .content(JSONObject.toJSONString(MsgCard))
                        .build())
                .build();
        // 发起请求
        PatchMessageResp resp = client.im().message().patch(req, RequestOptions.newBuilder()
                .build());

        // 处理服务端错误
        if (!resp.success()) {
            System.out.println(String.format("code:%s,msg:%s,reqId:%s", resp.getCode(), resp.getMsg(), resp.getRequestId()));
            return;
        }


    }
    // 构建卡片响应
    private static FeishuParams.MessageCard getAI_RobotCard(String msg, String note) {
        FeishuParams.MessageCard MessageCard = new FeishuParams.MessageCard();
        // 配置
        FeishuParams.Config config = new FeishuParams.Config();
        config.setWide_screen_mode(false);
        config.setEnable_forward(true);
        config.setUpdate_multi(false);
        List<FeishuParams.Element> Elements=new ArrayList<>();
        if(!msg.isEmpty()){
            Elements.add(withMainMd(msg));
        }
        Elements.add(withNote(note));
        MessageCard.setConfig(config);
        MessageCard.setElements(Elements);
        return MessageCard;
    }
    /**
     * 回复飞书卡片消息
     */
    public static void replyCard(FeishuParams.Flyingbookevent flyingbookevent) throws Exception {
        FeishuParams.MessageCard MsgCard = getAI_RobotCard("", "正在思考，请稍等...");
        String SendAppid = flyingbookevent.getHeader().getAppid();
        // 构建client
        Client client = Client.newBuilder(SendAppid, getAppSecret(SendAppid))
                .build();
        // 创建请求对象
        ReplyMessageReq req = ReplyMessageReq.newBuilder()
                .messageId(flyingbookevent.getEvent().getMessage().getMessage_id())//回复消息id
                .replyMessageReqBody(ReplyMessageReqBody.newBuilder()
                        .msgType("interactive")
                        .uuid(UUID.randomUUID().toString())
                        .content(JSONObject.toJSONString(MsgCard))
                        .build())
                .build();

        // 发起请求
        ReplyMessageResp resp = client.im().message().reply(req, RequestOptions.newBuilder()
                .build());
        // 处理服务端错误
        if (!resp.success()) {
            System.out.println(String.format("code:%s,msg:%s,reqId:%s", resp.getCode(), resp.getMsg(), resp.getRequestId()));
            return;
        }
       MessageId= resp.getData().getMessageId();
    }



    //鉴权url
    public static String getAuthorizationUrl(String hostUrl, String apikey, String apisecret) throws Exception {
        //获取host
        URL url = new URL(hostUrl);
        //获取鉴权时间 date
        SimpleDateFormat format = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z", Locale.US);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));
        String date = format.format(new Date());
        //获取signature_origin字段
        String builder = "host: " + url.getHost() + "\n" +
                "date: " + date + "\n" +
                "GET " + url.getPath() + " HTTP/1.1";
        //获得signatue
        Charset charset = StandardCharsets.UTF_8;
        Mac mac = Mac.getInstance("hmacsha256");
        SecretKeySpec sp = new SecretKeySpec(apisecret.getBytes(charset), "hmacsha256");
        mac.init(sp);
        byte[] basebefore = mac.doFinal(builder.getBytes(charset));
        String signature = Base64.getEncoder().encodeToString(basebefore);
        //获得 authorization_origin
        String authorization_origin = String.format("api_key=\"%s\",algorithm=\"%s\",headers=\"%s\",signature=\"%s\"", apikey, "hmac-sha256", "host date request-line", signature);
        //获得authorization
        String authorization = Base64.getEncoder().encodeToString(authorization_origin.getBytes(charset));
        //获取httpurl
        HttpUrl httpUrl = HttpUrl.parse("https://" + url.getHost() + url.getPath()).newBuilder().//
                addQueryParameter("authorization", authorization).//
                addQueryParameter("date", date).//
                addQueryParameter("host", url.getHost()).//
                build();

        return httpUrl.toString();
    }


    //重写onopen
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        webSocket.send(getMsg());

    }

    private String getMsg() {
        JSONObject frame = new JSONObject();
        JSONObject header = new JSONObject();
        JSONObject chat = new JSONObject();
        JSONObject parameter = new JSONObject();
        JSONObject payload = new JSONObject();
        JSONObject message = new JSONObject();
        JSONObject text = new JSONObject();
        JSONArray ja = new JSONArray();

        //填充header
        header.put("app_id", APPID);
        header.put("uid", userId);
        //填充parameter
        chat.put("domain", "generalv2");
        chat.put("random_threshold", 0);
        chat.put("max_tokens", 1024);
        chat.put("auditing", "default");
        parameter.put("chat", chat);
        //填充payload
        text.put("role", "user");
        text.put("content", question);
        ja.add(text);
        message.put("text", ja);
        payload.put("message", message);
        frame.put("header", header);
        frame.put("parameter", parameter);
        frame.put("payload", payload);
        return frame.toJSONString();
    }

    //重写onmessage
    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        ResponseData responseData = JSONObject.parseObject(text, ResponseData.class);
        if (0 == responseData.getHeader().getInteger("code")) {
            if (2 != responseData.getHeader().getInteger("status")) {
                Payload pl = responseData.getPayload().toJavaObject(Payload.class);
                JSONArray temp = pl.getChoices().getJSONArray("text");
                JSONObject jo = temp.getJSONObject(0);
                answer += jo.get("content");
                try {
                    updateTextCard("正在处理，请稍等...");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                Payload pl1 = responseData.getPayload().toJavaObject(Payload.class);
                JSONArray temp1 = pl1.getChoices().getJSONArray("text");
                JSONObject jo = temp1.getJSONObject(0);
                answer += jo.get("content");
                try {
                    updateTextCard("处理完成,有疑问可联系管理员");
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                webSocket.close(0, "客户端主动断开链接");
            }
        } else {

            this.answer = "**处理失败**";
            try {
                updateTextCard("处理失败,请联系管理员解决异常");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

    }

    //重写onFailure

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        super.onFailure(webSocket, t, response);
    }

    @Data
    static class ResponseData {
        private JSONObject header;
        private JSONObject payload;

        public JSONObject getHeader() {
            return header;
        }

        public JSONObject getPayload() {
            return payload;
        }
    }

    @Data
    static
    class Header {
        private int code;
        private String message;
        private String sid;
        private String status;

        public int getCode() {
            return code;
        }

        public String getMessage() {
            return message;
        }

        public String getSid() {
            return sid;
        }

        public String getStatus() {
            return status;
        }
    }

    @Data
    static class Payload {
        private JSONObject choices;
        private JSONObject usage;

        public JSONObject getChoices() {
            return choices;
        }

        public JSONObject getUsage() {
            return usage;
        }
    }

    class Choices {
        private int status;
        private int seq;
        private JSONArray text;

        public int getStatus() {
            return status;
        }

        public int getSeq() {
            return seq;
        }

        public JSONArray getText() {
            return text;
        }
    }
}
