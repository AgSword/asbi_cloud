package com.agsword.auth.controller;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.Map;

/**
 * @Description 获取RSA公钥接口
 * @projectName: ASBi
 * @package: com.agsword.auth.controller
 * @className: KeyPairController
 * @author: LiYinjian
 * @date: 2023/6/29 20:55
 * @version: 1.0
 */
@Slf4j
@RestController
@RequestMapping("/rsa")
public class KeyPairController {
    @Autowired
    private KeyPair keyPair;

    @GetMapping("/publicKey")
    public Map<String, Object> getKey() {
        log.info("/publicKey/");
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAKey key = new RSAKey.Builder(publicKey).build();
        return new JWKSet(key).toJSONObject();
    }
}
