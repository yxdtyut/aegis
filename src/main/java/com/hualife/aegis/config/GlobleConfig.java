package com.hualife.aegis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.HeaderHttpSessionIdResolver;
import org.springframework.session.web.http.HttpSessionIdResolver;

/**
 * @Author : yangxudong
 * @Description :   全局配置类
 * @Date : 上午10:09 2018/9/21
 */
@Configuration
public class GlobleConfig {
    @Bean
    public HttpSessionIdResolver httpSessionIdResolver() {
        return HeaderHttpSessionIdResolver.authenticationInfo();
    }
}
