package com.agsword.gateway.authorization;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.text.AntPathMatcher;
import com.agsword.gateway.config.IgnoreUrlsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description 鉴权管理器，用于判断是否有资源的访问权限
 * @projectName: ASBi
 * @package: com.agsword.gateway.authorization
 * @className: AuthorizationManager
 * @author: LiYinjian
 * @date: 2023/6/29 22:07
 * @version: 1.0
 */

@Component
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private IgnoreUrlsConfig ignoreUrlsConfig;

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext object) {
        ServerHttpRequest request = object.getExchange().getRequest();
        URI uri = request.getURI();
        AntPathMatcher pathMatcher = new AntPathMatcher();
        List<String> urls = ignoreUrlsConfig.getUrls();
        // 白名单直接放行
        for (String ignoreUrl: urls) {
            if (pathMatcher.match(ignoreUrl, uri.getPath())) {
                return Mono.just(new AuthorizationDecision(true));
            }
        }
        // 对应跨域的预检请求直接放行
        if (request.getMethod() == HttpMethod.OPTIONS) {
            return Mono.just(new AuthorizationDecision(true));
        }
        return authentication.map(authentication1 -> {return new AuthorizationDecision(true);});
//        Object obj = redisTemplate.opsForHash().get("oauth2:oauth_urls", uri.getPath());
//        List<String> authorities = Convert.toList(String.class, obj);
//        authorities = authorities.stream().map(i -> i = "ROLE_" + i).collect(Collectors.toList());
//        authorities.forEach(System.out::println);
//        return authentication.filter(Authentication::isAuthenticated)
//                .flatMapIterable(Authentication::getAuthorities)
//                .map(GrantedAuthority::getAuthority)
//                .any(authorities::contains)
//                .map(AuthorizationDecision::new)
//                .defaultIfEmpty(new AuthorizationDecision(false));
    }


}
