package com.cuit.studentskilldisplaysystem.common;

public enum StatusResponseCode {
    SUCCESS("1", "成功"),
    ERROR("0", "失败"),
    PARAMS_ERROR("40000", "请求参数错误"),
    NOT_LOGIN_ERROR("40100", "未登录"),
    NO_AUTH_ERROR("40101", "无权限"),
    NOT_FOUND_ERROR("40400", "请求数据不存在"),
    FORBIDDEN_ERROR("40300", "禁止访问"),
    SYSTEM_ERROR("50000", "系统内部异常"),
    OPERATION_ERROR("50001", "操作失败");

    /**
     * 异常状态码.正常情况下没有值
     */
    private String code;

    /**
     * 异常状态下地异常描述.正常情况下没有值
     */
    private String msg;

    StatusResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}