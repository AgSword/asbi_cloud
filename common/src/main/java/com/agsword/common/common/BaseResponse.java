package com.agsword.common.common;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.agsword.common.utils
 * @className: BaseResponse
 * @author: LiYinjian
 * @date: 2023/6/5 1:01
 * @version: 1.0
 */

@Data
@NoArgsConstructor
public class BaseResponse<T> implements Serializable {
    private static final long serialVersionUID = 3001538641430990914L;
    private int code;

    private T data;

    private String message;

    public BaseResponse(int code, T data, String message) {
        this.code = code;
        this.data = data;
        this.message = message;
    }

    public BaseResponse(int code, T data) {
        this(code, data, "");
    }

    public BaseResponse(ErrorCode errorCode) {
        this(errorCode.getCode(), null, errorCode.getMessage());
    }
}
