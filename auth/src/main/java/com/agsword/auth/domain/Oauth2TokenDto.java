package com.agsword.auth.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.agsword.auth.domain
 * @className: Oauth2TokenDto
 * @author: LiYinjian
 * @date: 2023/7/2 21:05
 * @version: 1.0
 */

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
public class Oauth2TokenDto {
    @ApiModelProperty("访问令牌")
    private String token;
    @ApiModelProperty("刷令牌")
    private String refreshToken;
    @ApiModelProperty("访问令牌头前缀")
    private String tokenHead;
    @ApiModelProperty("有效时间（秒）")
    private int expiresIn;
}
