package com.agsword.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.agsword.common.dto
 * @className: UserDTO
 * @author: LiYinjian
 * @date: 2022/5/5 1:01
 * @version: 1.0
 */

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class UserDTO implements Serializable {

    private static final long serialVersionUID = -610696972693753029L;
    /**
     * id
     */
    private Long id;
    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 用户角色
     */
    private String userRole;

}
