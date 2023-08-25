package com.example.demo.config.ErrorResolve;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ResultCodeEnum {
    // 请求成功
    SUCCESS("200"),
    // 请求失败
    FAIL("400"),
    // EXCEL 导入失败
    EXCEL_FAIL("401"),
    // 参数为空
    PARAM_ERROR("402"),
    // userID 为空
    ID_NULL("101"),
    // 前端传的实体为空
    MODEL_NULL("102"),
    // 更新失败
    UPDATE_FAIL("103"),
    // 代码内部异常
    CODE_EXCEPTION("500", "服务器错误或代码内部异常");

    /**
     * 状态码
     */
    private String code;

    public String getCode() {
        return code;
    }

    ResultCodeEnum(String code) {
        this.code = code;
    }

    private String msg;

    public String getMsg() {
        return msg;
    }

}

