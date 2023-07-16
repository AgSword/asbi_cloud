package com.agsword.common.utils;

import com.agsword.common.common.BusinessException;
import com.agsword.common.common.ErrorCode;

/**
 * @Description 抛异常工具类
 * @projectName: ASBi
 * @package: com.agsword.common.utils
 * @className: ThrowUtils
 * @author: LiYinjian
 * @date: 2022/5/5 1:01
 * @version: 1.0
 */

public class ThrowUtils {

    /**
     * 条件成立则抛异常
     * @param condition
     * @param exception
     */
    public static void throwIf(boolean condition, RuntimeException exception) {
        if(condition){
            throw exception;
        }
    }

    /**
     * 条件成立则抛异常
     * @param condition
     * @param errorCode
     */
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }

    /**
     * 条件成立则抛异常
     * @param condition
     * @param errorCode
     * @param message
     */
    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }
}
