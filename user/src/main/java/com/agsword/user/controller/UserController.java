package com.agsword.user.controller;

import com.agsword.common.common.BaseResponse;
import com.agsword.common.common.BusinessException;
import com.agsword.common.common.ErrorCode;
import com.agsword.common.dto.UserDTO;
import com.agsword.common.entity.User;
import com.agsword.common.utils.ResultUtils;
import com.agsword.user.dto.UserLoginRequest;
import com.agsword.user.dto.UserRegisterRequest;
import com.agsword.user.service.AuthService;
import com.agsword.user.service.IUserService;
import com.agsword.user.vo.LoginUserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author lyj
 * @since 2023-06-08
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Resource
    private IUserService userService;

    @Resource
    private AuthService authService;

    /**
     * 用户注册
     *
     * @param userRegisterRequest
     * @return
     */
    @PostMapping("/register")
    public BaseResponse<Long> userRegister(@RequestBody UserRegisterRequest userRegisterRequest) {
        if (userRegisterRequest == null) {
            throw new BusinessException(ErrorCode.PARMAS_ERROR);
        }
        String userAccount = userRegisterRequest.getUserAccount();
        String userPassword = userRegisterRequest.getUserPassword();
        String checkPassword = userRegisterRequest.getCheckPassword();
        if (StringUtils.isAllBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARMAS_ERROR);
        }
        long result = userService.userRegister(userAccount, userPassword, checkPassword);
        return ResultUtils.success(result);
    }

    /**
     * 用户登录
     *
     * @param userLoginRequest
     * @return
     */
    @PostMapping("/login")
    public BaseResponse<LoginUserVO> userLogin(@RequestBody UserLoginRequest userLoginRequest) {
        if (userLoginRequest == null) {
            throw new BusinessException((ErrorCode.PARMAS_ERROR));
        }
        String userAccount = userLoginRequest.getUserAccount();
        String userPassword = userLoginRequest.getUserPassword();
        System.out.println(userAccount);
        System.out.println(userPassword);
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARMAS_ERROR);
        }
        Map<String, String> params = new HashMap<>();
        params.put("client_secret", "123456");
        params.put("grant_type", "password");
        params.put("client_id", "client-app");
        params.put("username", userAccount);
        params.put("password", userPassword);
        return authService.getAccessToken(params);
    }

    /**
     * 用户注销
     *
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public BaseResponse<Boolean> userLogout(HttpServletRequest request) {
        if (request == null) {
            throw new BusinessException(ErrorCode.PARMAS_ERROR);
        }
        boolean result = userService.userLogout(request);
        return ResultUtils.success(result);
    }

    @GetMapping("/loadByUsername")
    public UserDTO loadByUsername(@RequestParam String userName) {
        if (userName == null) {
            throw new BusinessException(ErrorCode.PARMAS_ERROR);
        }
        return userService.loadByUsername(userName);
    }
}



















