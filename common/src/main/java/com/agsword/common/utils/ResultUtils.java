package com.agsword.common.utils;

import com.agsword.common.common.BaseResponse;
import com.agsword.common.common.ErrorCode;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.agsword.common.utils
 * @className: ResultUtils
 * @author: LiYinjian
 * @date: 2022/5/5 1:01
 * @version: 1.0
 */

public class ResultUtils {
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<T>(0, data, "ok");
    }

    public static BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    public static BaseResponse error(int code, String message) {
        return new BaseResponse<>(code, null, message);
    }

    public static BaseResponse error(ErrorCode errorCode, String message) {
        return new BaseResponse(errorCode.getCode(), null, message);
    }


}
