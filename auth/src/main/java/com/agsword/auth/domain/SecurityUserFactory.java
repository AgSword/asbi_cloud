package com.agsword.auth.domain;

import com.agsword.common.dto.UserDTO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Description SecurityUser工厂类
 * @projectName: ASBi
 * @package: com.agsword.auth.domain
 * @className: SecurityUserFactory
 * @author: LiYinjian
 * @date: 2023/6/15 15:34
 * @version: 1.0
 */

public final class SecurityUserFactory {

    private SecurityUserFactory() {

    }

    public static SecurityUser create(UserDTO userDTO) {
        return new SecurityUser(
                userDTO.getId(),
                userDTO.getUserName(),
                userDTO.getUserPassword(),
                mapToGrantedAuthories(userDTO.getUserRole())
        );
    }


    private static List<GrantedAuthority> mapToGrantedAuthories(String role) {
        return Stream.of(role).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
