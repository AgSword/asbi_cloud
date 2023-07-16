package com.agsword.chart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@MapperScan("com.agsword.common.mapper")
@EnableDiscoveryClient
public class ChartApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChartApplication.class, args);
    }

}
