package com.agsword.user.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.agsword.user.vo
 * @className: LoginUserVO
 * @author: LiYinjian
 * @date: 2022/5/5 1:01
 * @version: 1.0
 */
@Data
public class LoginUserVO implements Serializable {

    private static final long serialVersionUID = -349872988424993797L;
    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户头像
     */
    private String userAvator;

    /**
     * 用户简介
     */
    private String userProfile;

    /**
     * 用户角色
     */
    private String userRole;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;


}
