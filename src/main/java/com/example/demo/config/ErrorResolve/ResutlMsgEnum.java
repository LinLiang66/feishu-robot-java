package com.example.demo.config.ErrorResolve;
public enum ResutlMsgEnum {
    //查询成功
    FIND_SUCCESS("查询成功！"),
    //查询失败
    FIND_FAIL("查询失败！"),
    //更新成功
    UPDATE_SUCCESS("更新成功"),
    //更新失败
    UPDATE_FAIL("更新成功"),

    SEND_SUCCESS("发送成功"),

    SEND_FAIL("发送失败"),
    EXECUTE_SUCCESS("响应成功"),
    EXECUTE_FAIL("响应失败");

    private String msg;

    ResutlMsgEnum(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
