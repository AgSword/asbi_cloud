package com.agsword.gateway.component;

import cn.hutool.json.JSONUtil;
import com.agsword.common.common.BaseResponse;
import com.agsword.common.common.ErrorCode;
import com.agsword.common.utils.ResultUtils;
import org.apache.http.HttpHeaders;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * @Description 没有登录或者token过期时
 * @projectName: ASBi
 * @package: com.agsword.gateway.component
 * @className: RestAuthticationEntryPoint
 * @author: LiYinjian
 * @date: 2023/6/30 9:11
 * @version: 1.0
 */

@Component
public class RestAuthticationEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        response.getHeaders().set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        response.getHeaders().set("Access-Control-Allow-Origin","*");
        response.getHeaders().set("Cache-Control","no-cache");
        String body = JSONUtil.toJsonStr(ResultUtils.error(ErrorCode.NOT_LOGIN_ERROR));
        DataBuffer buffer = response.bufferFactory().wrap(body.getBytes(Charset.forName("UTF-8")));
        return response.writeWith(Mono.just(buffer));
    }
}
