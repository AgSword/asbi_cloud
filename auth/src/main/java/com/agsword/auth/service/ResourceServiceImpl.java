package com.agsword.auth.service;

import cn.hutool.core.collection.CollUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.agsword.auth.service
 * @className: ResourceServiceImpl
 * @author: LiYinjian
 * @date: 2023/7/15 15:29
 * @version: 1.0
 */

@Service
public class ResourceServiceImpl {

    private Map<String, List<String>> resourceRolesMap;
    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @PostConstruct
    public void initData() {
        resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/chart/chart/list/page", CollUtil.toList("USER","ADMIN"));
        resourceRolesMap.put("/api/user/currentUser", CollUtil.toList("USER", "ADMIN"));
        redisTemplate.opsForHash().putAll("oauth2:oauth_urls", resourceRolesMap);
    }
}

