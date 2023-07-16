package com.agsword.auth.component;

import com.agsword.auth.domain.SecurityUser;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Component;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description Jwt内容增强器
 * @projectName: ASBi
 * @package: com.agsword.auth.component
 * @className: JwtTokenEnhancer
 * @author: LiYinjian
 * @date: 2023/6/15 19:30
 * @version: 1.0
 */

@Component
public class JwtTokenEnhancer implements TokenEnhancer{
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken,
                                     OAuth2Authentication oAuth2Authentication) {
        SecurityUser securityUser = (SecurityUser) oAuth2Authentication.getPrincipal();
        Map<String, Object> info = new HashMap<>();
        // 将用户id存入jwt中
        info.put("id",securityUser.getId());
        ((DefaultOAuth2AccessToken) oAuth2AccessToken).setAdditionalInformation(info);
        return oAuth2AccessToken;
    }
}
