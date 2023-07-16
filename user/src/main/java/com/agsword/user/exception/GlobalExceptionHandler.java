package com.agsword.user.exception;

import com.agsword.common.common.BaseResponse;
import com.agsword.common.common.BusinessException;
import com.agsword.common.common.ErrorCode;
import com.agsword.common.utils.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.example.chart.exception
 * @className: GlobalExceptionHandler
 * @author: LiYinjian
 * @date: 2022/5/5 1:01
 * @version: 1.0
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error("BusinessException", e);
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("runtimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }
}
