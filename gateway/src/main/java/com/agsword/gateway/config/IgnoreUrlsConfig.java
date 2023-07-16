package com.agsword.gateway.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description
 * @projectName: ASBi
 * @package: com.agsword.gateway.config
 * @className: IgnoreUrlsConfig
 * @author: LiYinjian
 * @date: 2023/6/30 7:09
 * @version: 1.0
 */

@Data
@EqualsAndHashCode(callSuper = false)
@Component
@ConfigurationProperties(prefix = "secure.ignore")
public class IgnoreUrlsConfig {
    private List<String> urls;
}
