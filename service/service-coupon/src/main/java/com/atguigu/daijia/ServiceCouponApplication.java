package com.atguigu.daijia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDateTime;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ServiceCouponApplication {
    private static Logger logger = LoggerFactory.getLogger(ServiceCouponApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ServiceCouponApplication.class, args);
        logger.info("服务启动成功,当前时间为:{}", LocalDateTime.now());
        logger.error("服务启动成功,当前时间为:{}", LocalDateTime.now());
    }
}
