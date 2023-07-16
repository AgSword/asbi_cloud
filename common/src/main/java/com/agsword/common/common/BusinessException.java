package com.agsword.common.common;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.agsword.common.exception
 * @className: BusinessException
 * @author: LiYinjian
 * @date: 2023/6/1 1:01
 * @version: 1.0
 */

public class BusinessException extends RuntimeException {
    private final int code;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }

}
