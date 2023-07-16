package com.agsword.user.service;

import com.agsword.common.common.BaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @Description 认证服务远程调用
 * @projectName: ASBi
 * @package: com.agsword.auth.service
 * @className: AuthService
 * @author: LiYinjian
 * @date: 2023/7/2 21:22
 * @version: 1.0
 */

@FeignClient("auth")
public interface AuthService {

    @PostMapping(value = "/oauth/token")
    BaseResponse getAccessToken(@RequestParam Map<String, String> parameters);

}
