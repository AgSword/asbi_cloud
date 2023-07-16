package com.agsword.user.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.agsword.user.dto.user
 * @className: UserRegisterRequest
 * @author: LiYinjian
 * @date: 2022/5/5 1:01
 * @version: 1.0
 */

@Data
public class UserRegisterRequest implements Serializable {

    private static final long serialVersionUID = -5643915488670602518L;

    private String userAccount;

    private String userPassword;

    private String checkPassword;
}
