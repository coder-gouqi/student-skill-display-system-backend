package com.cuit.studentskilldisplaysystem.exception;


import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;

/**
 * 自定义异常类
 */
public class BusinessException extends RuntimeException {

    /**
     * 错误码
     */
    private final String code;

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(StatusResponseCode StatusResponseCode) {
        super(StatusResponseCode.getMsg());
        this.code = StatusResponseCode.getCode();
    }

    public BusinessException(StatusResponseCode StatusResponseCode, String message) {
        super(message);
        this.code = StatusResponseCode.getCode();
    }

    public String getCode() {
        return code;
    }
}
