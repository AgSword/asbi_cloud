package com.agsword.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.agsword.user.dto.user
 * @className: UserLoginRequest
 * @author: LiYinjian
 * @date: 2022/5/5 1:01
 * @version: 1.0
 */

@Data
@NoArgsConstructor
public class UserLoginRequest implements Serializable {

    private static final long serialVersionUID = 2241075973603133502L;

    private String userAccount;

    private String userPassword;
}
