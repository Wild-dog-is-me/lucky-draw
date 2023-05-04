package org.dog.common.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dog.common.interceptor.SecurityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Odin
 * @Date: 2023/5/5 03:06
 * @Description:
 */
@Slf4j
@Configuration
@AllArgsConstructor
public class MyWebMvcConfig implements WebMvcConfigurer {

    private final SecurityInterceptor securityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(securityInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login", "/**/register");
    }
}
