package com.cuit.studentskilldisplaysystem.exception;


import com.cuit.studentskilldisplaysystem.common.StatusResponseCode;

/**
 * 抛异常工具类
 */
public class ThrowUtils {

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param runtimeException
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param StatusResponseCode
     */
    public static void throwIf(boolean condition, StatusResponseCode StatusResponseCode) {
        throwIf(condition, new BusinessException(StatusResponseCode));
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param StatusResponseCode
     * @param message
     */
    public static void throwIf(boolean condition, StatusResponseCode StatusResponseCode, String message) {
        throwIf(condition, new BusinessException(StatusResponseCode, message));
    }
}
