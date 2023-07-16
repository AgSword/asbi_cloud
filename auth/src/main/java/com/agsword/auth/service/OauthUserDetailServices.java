package com.agsword.auth.service;

import com.agsword.auth.constant.MessageConstant;
import com.agsword.auth.domain.SecurityUserFactory;
import com.agsword.common.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.agsword.auth.service
 * @className: OauthUserDetailServices
 * @author: LiYinjian
 * @date: 2023/6/5 1:01
 * @version: 1.0
 */

@Service
@Slf4j
public class OauthUserDetailServices implements UserDetailsService {

    @Resource
    MemberService memberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("/loadUserByUsername");
        UserDTO userDTO = memberService.loadByUsername(username);
        log.info("/loadUserByUsername/");
        if (userDTO == null) {
            throw new UsernameNotFoundException(MessageConstant.USERNAME_PASSWORD_ERROR);
        }
        return SecurityUserFactory.create(userDTO);
    }
}
