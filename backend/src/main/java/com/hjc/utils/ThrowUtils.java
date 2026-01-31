package com.hjc.utils;

import com.hjc.common.exception.BusinessException;
import com.hjc.enums.CodeEnum;

/**
 * 抛异常工具类
 *
 * @author 观止study
 *  @from https://blog.csdn.net/m0_66570338/article/details/132145086
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
     * @param errorCode
     */
    public static void throwIf(boolean condition, CodeEnum errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }

    /**
     * 条件成立则抛异常
     *
     * @param condition
     * @param errorCode
     * @param message
     */
    public static void throwIf(boolean condition, CodeEnum errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }


    /**
     * 条件成立则抛异常
     *
     * @param data
     */
    public static void throwIfNull(Object data) {
        throwIf(data == null, CodeEnum.PARAMS_NULL);
    }
}
