package com.agsword.auth.exception;

import com.agsword.common.common.BaseResponse;
import com.agsword.common.common.ErrorCode;
import com.agsword.common.utils.ResultUtils;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.agsword.auth.exception
 * @className: Oauth2ExceptionHandler
 * @author: LiYinjian
 * @date: 2023/6/29 21:37
 * @version: 1.0
 */

@ControllerAdvice
public class Oauth2ExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = OAuth2Exception.class)
    public BaseResponse<?> handleOauth2(OAuth2Exception e) {
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR,e.getMessage());
    }
}
