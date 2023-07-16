package com.agsword.user.service.impl;

import cn.hutool.crypto.digest.BCrypt;
import com.agsword.common.common.BusinessException;
import com.agsword.common.common.ErrorCode;
import com.agsword.common.constant.CommonConstant;
import com.agsword.common.dto.UserDTO;
import com.agsword.common.mapper.UserMapper;
import com.agsword.common.entity.User;
import com.agsword.common.utils.SqlUtils;
import com.agsword.user.dto.UserQueryRequest;
import com.agsword.user.service.IUserService;
import com.agsword.user.vo.LoginUserVO;
import com.agsword.user.vo.UserVO;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author lyj
 * @since 2023-06-08
 */
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public long userRegister(String userAccount, String userPassword, String checkPassword) {
        // 1. 参数校验
        if (StringUtils.isAnyBlank(userAccount, userPassword, checkPassword)) {
            throw new BusinessException(ErrorCode.PARMAS_ERROR, "参数错误");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARMAS_ERROR, "用户账户过短");
        }
        if (userPassword.length() < 8 || checkPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARMAS_ERROR, "用户密码过短");
        }
        // 2. 验证密码和确认密码一致
        if (!userPassword.equals(checkPassword)) {
            throw new BusinessException(ErrorCode.PARMAS_ERROR, "两次密码输入不一致");
        }
        // 3. 验证账户不能重复；加密；插入数据。这些操作需要同步。
        synchronized (userAccount.intern()) {
            // 账号不能重复
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(User::getUserAccount, userAccount);
            Long count = this.baseMapper.selectCount(wrapper);
            if (count > 0) {
                throw new BusinessException(ErrorCode.PARMAS_ERROR, "账号重复");
            }
            // 插入数据
            User user = new User();
            user.setUserAccount(userAccount);
            user.setUserPassword(userPassword);
            boolean saveResult = this.save(user);
            if (!saveResult) {
                throw new BusinessException(ErrorCode.SYSTEM_ERROR, "注册失败，数据库错误");
            }
            // 用户密码加密
            String hashpw = BCrypt.hashpw(userPassword);
//            String s = DigestUtils.md5DigestAsHex((userPassword + user.getId()).getBytes());
            LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
            updateWrapper.eq(User::getId, user.getId()).set(User::getUserPassword, hashpw);
            this.baseMapper.update(null, updateWrapper);
            return user.getId();
        }

    }

    @Override
    public LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request) {
        // 1. 参数校验
        if (StringUtils.isAnyBlank(userAccount, userPassword)) {
            throw new BusinessException(ErrorCode.PARMAS_ERROR, "参数为空");
        }
        if (userAccount.length() < 4) {
            throw new BusinessException(ErrorCode.PARMAS_ERROR, "账号错误");
        }
        if (userPassword.length() < 8) {
            throw new BusinessException(ErrorCode.PARMAS_ERROR, "密码错误");
        }
        // 2. 查询此账号是否存在
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserAccount, userAccount);
        User user = this.baseMapper.selectOne(queryWrapper);
        if (user == null) {
            log.info("user login failed, user does not exist");
            throw new BusinessException(ErrorCode.PARMAS_ERROR, "用户不存在");
        }
        // 3. 校验密码
        String s = DigestUtils.md5DigestAsHex((userPassword + user.getId()).getBytes());
        queryWrapper.eq(User::getUserPassword, s);
        User user1 = this.baseMapper.selectOne(queryWrapper);
        if (user1 == null) {
            log.info("user login failed, userAccount cannot match userPassword");
            throw new BusinessException(ErrorCode.PARMAS_ERROR, "密码错误");
        }
        // 4. 记录用户的登录态
        request.getSession().setAttribute("user_login", user1);
        return this.getLoginUserVO(user1);
    }

    /**
     * 获取当前登录用户
     * @param request
     * @return
     */
    @Override
    public User getLoginUser(HttpServletRequest request) {
        // 先判断是否已登录
        Object userLogin = request.getAttribute("user_login");
        User currentUser = (User) userLogin;
        if (currentUser == null || currentUser.getId() == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        // 从数据库查询（追求性能的话可以注释，直接走缓存）
        Long userId = currentUser.getId();
        currentUser = this.getById(userId);
        if (currentUser == null){
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return currentUser;
    }

    /**
     * 获取当前登录用户（允许未登录）
     * @param request
     * @return
     */
    @Override
    public User getLoginUserPermitNull(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute("user_login");
        User currentUser = (User) userObj;
        if (currentUser == null || currentUser.getId() == null) {
            return null;
        }
        // 从数据库查询（追求性能的话可以注释，直接走缓存）
        long userId = currentUser.getId();
        return this.getById(userId);
    }

    @Override
    public boolean isAdmin(HttpServletRequest request) {
        Object userLogin = request.getSession().getAttribute("user_login");
        User user = (User) userLogin;
        return isAdmin(user);
    }

    @Override
    public boolean isAdmin(User user) {
        return user!= null && user.getUserRole().equals("admin");
    }

    @Override
    public boolean userLogout(HttpServletRequest request) {
        if (request.getSession().getAttribute("user_login") == null){
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "未登录");
        }
        // 移除登录态
        request.getSession().removeAttribute("user_login");
        return true;
    }

    @Override
    public LoginUserVO getLoginUserVO(User user) {
        if (user == null) {
            return null;
        }
        LoginUserVO loginUserVO = new LoginUserVO();
        BeanUtils.copyProperties(user, loginUserVO);
        return loginUserVO;
    }

    @Override
    public UserVO getUserVO(User user) {
        if(user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }

    @Override
    public List<UserVO> getUserVO(List<User> userList) {
        if (CollectionUtils.isEmpty(userList)) {
            return new ArrayList<>();
        }
        return userList.stream().map(this::getUserVO).collect(Collectors.toList());
    }

    @Override
    public QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest) {
        if(userQueryRequest == null) {
            throw new BusinessException(ErrorCode.PARMAS_ERROR,"请求参数为空");
        }
        Long id = userQueryRequest.getId();
        String unionId = userQueryRequest.getUnionId();
        String mpOpenId = userQueryRequest.getMpOpenId();
        String userName = userQueryRequest.getUserName();
        String userProfile = userQueryRequest.getUserProfile();
        String userRole = userQueryRequest.getUserRole();
        String sortField = userQueryRequest.getSortField();
        String sortOrder = userQueryRequest.getSortOrder();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(id != null, "id", id);
        queryWrapper.eq(StringUtils.isNotBlank(unionId), "unionId", unionId);
        queryWrapper.eq(StringUtils.isNotBlank(mpOpenId), "mpOpenId", mpOpenId);
        queryWrapper.eq(StringUtils.isNotBlank(userRole), "userRole", userRole);
        queryWrapper.like(StringUtils.isNotBlank(userProfile), "userProfile", userProfile);
        queryWrapper.like(StringUtils.isNotBlank(userName), "userName", userName);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);
        return queryWrapper;
    }

    @Override
    public UserDTO loadByUsername(String userName) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUserAccount, userName);
        User user = this.baseMapper.selectOne(queryWrapper);
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }
}
