package com.agsword.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * @Description 全局跨域配置  注意：前端从网关进行调用时需要配置
 * @projectName: ASBi
 * @package: com.agsword.gateway.config
 * @className: GlobalCorsConfig
 * @author: LiYinjian
 * @date: 2023/7/8 22:39
 * @version: 1.0
 */

@Configuration
public class GlobalCorsConfig {

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        CorsConfiguration corsConfig = new CorsConfiguration();

        // 允许所有请求方法
        corsConfig.addAllowedMethod("*");
        // 允许所有域，当请求头
        corsConfig.addAllowedOriginPattern("http://localhost:8000");
        // 允许全部请求头
        corsConfig.addAllowedHeader("*");
        // 允许携带 Authorization 头
        corsConfig.setAllowCredentials(true);
        // 允许全部请求路径
        source.registerCorsConfiguration("/**", corsConfig);

        return source;
    }

}
