package com.agsword.auth.controller;

import com.agsword.auth.domain.Oauth2TokenDto;
import com.agsword.common.common.BaseResponse;
import com.agsword.common.utils.ResultUtils;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.endpoint.TokenEndpoint;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 自定义Oauth2获取令牌接口
 * @projectName: ASBi
 * @package: com.agsword.auth.controller
 * @className: AuthController
 * @author: LiYinjian
 * @date: 2023/7/2 19:32
 * @version: 1.0
 */

@Slf4j
@RestController
@RequestMapping("/oauth")
public class AuthController {

    @Autowired
    private TokenEndpoint tokenEndpoint;

    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public BaseResponse<Oauth2TokenDto> postAccessToken(HttpServletRequest request,
                                                        @ApiParam("授权模式") @RequestParam String grant_type,
                                                        @ApiParam("Oauth2客户端秘钥") @RequestParam String client_secret,
                                                        @ApiParam("Oauth2客户端ID") @RequestParam String client_id,
                                                        @ApiParam("刷新token") @RequestParam(required = false) String refresh_token,
                                                        @ApiParam("登录用户名") @RequestParam(required = false) String username,
                                                        @ApiParam("登录密码") @RequestParam(required = false) String password) throws HttpRequestMethodNotSupportedException {
        log.info("认证服务器：/oauth/token");
        Map<String, String> parameters = new HashMap<>();
        parameters.put("grant_type", grant_type);
        parameters.put("client_secret", client_secret);
        parameters.put("client_id", client_id);
        parameters.putIfAbsent("refresh_token", refresh_token);
        parameters.putIfAbsent("username", username);
        parameters.putIfAbsent("password", password);
        OAuth2AccessToken oAuth2AccessToken = tokenEndpoint.postAccessToken(request.getUserPrincipal(), parameters).getBody();
        Oauth2TokenDto oauth2TokenDto = Oauth2TokenDto.builder()
                .token(oAuth2AccessToken.getValue())
                .refreshToken(oAuth2AccessToken.getRefreshToken().getValue())
                .expiresIn(oAuth2AccessToken.getExpiresIn())
                .tokenHead("Bearer ").build();
        log.info("认证服务器11：/oauth/token");
        return ResultUtils.success(oauth2TokenDto);
    }
}
