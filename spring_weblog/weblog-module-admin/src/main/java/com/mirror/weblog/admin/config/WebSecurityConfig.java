package com.mirror.weblog.admin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author mirror
 * 继承的是一个包装类
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 这个重写是过滤器，对于请求进行一个过滤
     * @param http 安全框架
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests()
                // 认证所有以 /admin 为前缀的 URL 资源
                .mvcMatchers("/admin/**").authenticated()
                // 其他都需要放行，无需认证
                .anyRequest().permitAll().and()
                // 使用表单登录
                .formLogin().and()
                // 使用 HTTP Basic 认证
                .httpBasic();
    }
}