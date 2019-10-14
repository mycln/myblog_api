package com.clouderwork.config;

import com.clouderwork.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @Author : xuqiang
 * @Description : 拦截器配置
 * @Date: 2018/4/24
 */
@Configuration
public class InterceptorConfiguration extends WebMvcConfigurerAdapter {

    @Bean
    LoginInterceptor localInterceptor() {
        return new LoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration login = registry.addInterceptor(localInterceptor());
        // 配置拦截的路径
        login.addPathPatterns("/**");
        // 不拦截的路径
        login.excludePathPatterns("/swagger-resources/**").excludePathPatterns("/v2/**");
        login.excludePathPatterns("/configuration/**").excludePathPatterns("/error/**");

        //图片显示
        login.excludePathPatterns("/file/**");
        login.excludePathPatterns("/common/back/**").excludePathPatterns("/common/upload/**")
                .excludePathPatterns("/common/app/**");

        super.addInterceptors(registry);
    }
}
