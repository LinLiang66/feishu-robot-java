package com.example.demo.entity;


import com.alibaba.fastjson.JSONObject;
import lombok.Getter;

import java.util.List;


/**
 * 飞书请求参数类
 *
 * @author Lin Liang
 * @version 1.0.0
 * @date 2023/07/28
 * @see
 */
public class FeishuParams {

    @lombok.Data
    public static class returnRequest {
        private String code;
        private String data;
        private String message;
    }

    @lombok.Data
    public static class Request {
        private String timestamp;
        private String sign;
        private Contents content;
        private String msg_type;
    }


    @lombok.Data
    public static class Contents {
        private String text;
    }

    @lombok.Data
    public static class Collectscan {
        /**
         * 网点代码
         */
        private String wdbm;
        /**
         * 二级网点代码
         */
        private String Wdbms;

        /**
         * 请求内容
         */
        private String msg;

    }

    @lombok.Data
    public static class Requestqihang {
        /**
         * 请求路径
         */
        private String path;

        /**
         * 请求内容
         */
        private JSONObject content;

    }


    /**
     * 出港处理数据推送卡片
     *
     * @author Lin Liang
     * @version 1.0.0
     * @date 2023/07/29
     * @see
     */
    @lombok.Data
    public static class FlyingbookPushcard {

        /**
         * 当前日期
         */
        private String current_date;
        /**
         * 追损单量
         */
        private int chasingLossNumber;
        /**
         * 追损金额
         */
        private int chasingLossAmount;
        /**
         * 完结单量
         */
        private int finishNumber;
        /**
         * 待完结单量
         */
        private int noFinishNumber;

        /**
         * 录单量
         */
        private int createNumber;
        /**
         * 红榜列表
         */
        private List<Pushcards> honorboard_table;
        /**
         * 黑榜列表
         */
        private List<Pushcards> blacklist_table;

        /**
         * 处理概况
         */
        private List<Pushcards> handlingsituation_table;


    }


    /**
     * 出港处理数据列表
     *
     * @author Lin Liang
     * @version 1.0.0
     * @date 2023/07/29
     * @see
     */
    @Getter
    @lombok.Data
    public static class Pushcards {
        /**
         * 追损单量
         */
        private int chasingLossNumber;
        /**
         * 追损金额
         */
        private int chasingLossAmount;
        /**
         * 完结单量
         */
        private int finishNumber;

        /**
         * 待完结单量
         */
        private int noFinishNumber;
        /**
         * 录单量
         */
        @Getter
        private int createNumber;
        /**
         * 序号
         */
        private int number;

        /**
         * 名字
         */
        private String nickName;

    }

    @lombok.Data
    public static class Flyingbookencryptionevent {
        /**
         * 加密内容
         */
        private String encrypt;
        /**
         * 加密内容
         */
        private String challenge;
        /**
         * 加密内容
         */
        private String token;
        /**
         * 加密内容
         */
        private String type;


    }

    /**
     * 飞书事件参数
     */
    @lombok.Data
    public static class Flyingbookevent {


        /**
         * 请求内容
         */
        private String challenge;
        /**
         * 令牌
         */
        private String token;
        /**
         * 类型
         */
        private String type;

        /**
         * 事件内容
         */
        private Event event;
        /**
         * 事件头
         */
        private Header header;
        /**
         * 事件模式
         */
        private String schema;

    }

    /**
     * 事件内容
     */
    @lombok.Data
    public static class Event {
        /**
         * 事件中包含的消息内容
         */
        private Message message;
        /**
         * 事件的发送者信息
         */
        private Sender sender;
    }


    /**
     * 事件中包含的消息内容
     */
    @lombok.Data
    public static class Message {

        /**
         * 消息所在的群组 ID
         */
        private String chatid;
        /**
         * 消息所在的群组类型p2p：单聊 group： 群组
         */
        private String chatType;
        /**
         * 消息内容, json 格式
         */
        private String content;
        /**
         * 消息发送时间（毫秒）
         */
        private String createTime;
        /**
         * 被@的用户的信息
         */
        private List<Mention> mentions;
        /**
         * 消息的open_message_id
         */
        private String message_id;
        /**
         * 消息类型
         */
        private String messageType;
        /**
         * 父消息的id，用于回复消息场景
         */
        private String parentid;
        /**
         * 根消息id，用于回复消息场景
         */
        private String rootid;
    }


    /**
     * 被@的用户的信息详情
     */
    @lombok.Data
    public static class Mention {
        /**
         * 用户 ID
         */
        private ID id;
        /**
         * mention key
         */
        private String key;
        /**
         * 用户姓名
         */
        private String name;
        /**
         * tenant key，为租户在飞书上的唯一标识，用来换取对应的tenant_access_token，也可以用作租户在应用里面的唯一标识
         */
        private String tenantKey;
    }


    /**
     * 用户 ID
     */
    @lombok.Data
    public static class ID {
        private String openid;
        private String unionid;
        private String userid;
    }


    /**
     * 事件的发送者信息
     */
    @lombok.Data
    public static class Sender {
        /**
         * 用户 ID
         */
        private Senderid senderid;
        /**
         * 消息发送者类型。目前只支持用户(user)发送的消息
         */
        private String senderType;
        /**
         * tenant key，为租户在飞书上的唯一标识，用来换取对应的tenant_access_token，也可以用作租户在应用里面的唯一标识
         */
        private String tenantKey;
    }


    /**
     * 用户 ID
     */
    @lombok.Data
    public static class Senderid {
        /**
         * 用户的 open id
         */
        private String open_id;
        /**
         * 用户的 union id
         */
        private String union_id;
        /**
         * 用户的 user id
         */
        private String user_id;
    }


    /**
     * 事件头
     */
    @lombok.Data
    public static class Header {
        /**
         * 应用 ID
         */
        private String appid;
        /**
         * 事件创建时间戳（单位：毫秒）
         */
        private String createTime;
        /**
         * 事件 ID
         */
        private String eventid;
        /**
         * 事件类型
         */
        private String eventType;
        /**
         * 租户 Key
         */
        private String tenantkey;
        /**
         * 事件 Token
         */
        private String token;
    }


    /**
     * 消息内容列表
     */
    @lombok.Data
    public static class Content {
        /**
         * 消息内容
         */
        private List<List<Contentdetails>> content;
        /**
         * 消息标题
         */
        private String title;
    }


    /**
     * 消息内容详情
     *
     * @author Lin Liang
     * @version 1.0.0
     * @date 2023/07/28
     * @see
     */
    @lombok.Data
    public static class Contentdetails {
        /**
         * 文件 key
         */
        private String fileKey;
        /**
         * 文件名
         */
        private String fileName;
        /**
         * 图片 key
         */
        private String imageKey;
        /**
         * 内容样式
         */
        private List<String> style;
        /**
         * 消息子类型
         */
        private String tag;
        /**
         * 消息内容
         */
        private String text;

    }


    /**
     * 发送消息内容详情
     *
     * @author Lin Liang
     * @version 1.0.0
     * @date 2023/07/28
     * @see
     */
    @lombok.Data
    public static class SendContentdetail {

        /**
         * 卡片消息专用
         */
        private Pushcard data;
        /**
         * 消息类型
         */
        private String type;
        /**
         * 文本消息内容
         */
        private String text;


    }


    /**
     * 卡片消息内容变量
     *
     * @author Lin Liang
     * @version 1.0.0
     * @date 2023/07/28
     * @see
     */
    @lombok.Data
    public static class Pushcard {

        /**
         * 卡片模板ID
         */
        private String template_id;
        /**
         * 卡片模板变量
         */
        private FlyingbookPushcard template_variable;

    }


    /**
     * 卡片信息
     *
     * @author Lin Liang
     * @version 1.0.0
     * @date 2023/10/11
     * @see
     */

    @lombok.Data
    public static class MessageCard {
        private Config config;
        private List<Element> elements;
        private CardHeader header;
    }




    @lombok.Data
    public  static  class Config {
        private boolean enable_forward;
        private boolean update_multi;
        private boolean wide_screen_mode;
    }
    @lombok.Data
    public   static  class Text {
        private String content;
        private String tag;
    }

    @lombok.Data
    public  static class Element {
        private String tag;
        private List<Element> elements;
        private Text text;
        private List<Field> fields;

        private String content;
    }



    @lombok.Data
    public  static class Field {
        private boolean is_short;
        private Text text;
    }



    @lombok.Data
    public  static class CardHeader {
        private String template;
        private Title title;
    }


    @lombok.Data
    public  static class Title {
        private String content;
        private String tag;
    }

}

