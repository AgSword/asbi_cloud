package com.agsword.auth.service;

import com.agsword.common.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.agsword.auth.service
 * @className: MemberService
 * @author: LiYinjian
 * @date: 2023/6/5 1:01
 * @version: 1.0
 */

@FeignClient("user")
public interface MemberService {

    @GetMapping(value = "/user/loadByUsername")
    UserDTO loadByUsername(@RequestParam String userName);
}
