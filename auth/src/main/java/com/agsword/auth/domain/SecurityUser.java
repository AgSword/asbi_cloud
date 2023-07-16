package com.agsword.auth.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.agsword.auth.domain
 * @className: SecurityUser
 * @author: LiYinjian
 * @date: 2023/6/15 14:52
 * @version: 1.0
 */

public class SecurityUser implements UserDetails {
    /**
     * id
     */
    private Long id;

    public Long getId() {
        return id;
    }

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 密码
     */
    private String userPassword;

    /**
     * 用户角色
     */
    private Collection<? extends GrantedAuthority> authorities;

    public SecurityUser(Long id, String userName, String userPassword,
                        Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return this.userPassword;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
