package com.mirror.weblog.admin.config;

import com.mirror.weblog.jwt.config.JwtAuthenticationSecurityConfig;
import com.mirror.weblog.jwt.filter.TokenAuthenticationFilter;
import com.mirror.weblog.jwt.handler.RestAccessDeniedHandler;
import com.mirror.weblog.jwt.handler.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

/**
 * @author mirror
 * 继承的是一个包装类
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 这个重写是过滤器，对于请求进行一个过滤
     * @param http 安全框架
     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests()
//                // 认证所有以 /admin 为前缀的 URL 资源
//                .mvcMatchers("/admin/**").authenticated()
//                // 其他都需要放行，无需认证
//                .anyRequest().permitAll().and()
//                // 使用表单登录
//                .formLogin().and()
//                // 使用 HTTP Basic 认证
//                .httpBasic();
//    }

//    @Resource
//    private JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable(). // 禁用 csrf
//                formLogin().disable() // 禁用表单登录
//                .apply(jwtAuthenticationSecurityConfig) // 设置用户登录认证相关配置
//                .and()
//                .authorizeHttpRequests()
//                .mvcMatchers("/admin/**").authenticated() // 认证所有以 /admin 为前缀的 URL 资源
//                .anyRequest().permitAll() // 其他都需要放行，无需认证
//                .and()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); // 前后端分离，无需创建会话
//    }

    @Resource
    private JwtAuthenticationSecurityConfig jwtAuthenticationSecurityConfig;
    @Resource
    private RestAuthenticationEntryPoint authEntryPoint;
    @Resource
    private RestAccessDeniedHandler deniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable(). // 禁用 csrf
                formLogin().disable() // 禁用表单登录
                .apply(jwtAuthenticationSecurityConfig) // 设置用户登录认证相关配置
                .and()
                .authorizeHttpRequests()
                .mvcMatchers("/admin/**").authenticated() // 认证所有以 /admin 为前缀的 URL 资源
                .anyRequest().permitAll() // 其他都需要放行，无需认证
                .and()
                .httpBasic().authenticationEntryPoint(authEntryPoint) // 处理用户未登录访问受保护的资源的情况
                .and()
                .exceptionHandling().accessDeniedHandler(deniedHandler) // 处理登录成功后访问受保护的资源，但是权限不够的情况
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 前后端分离，无需创建会话
                .and()
                .addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class) // 将 Token 校验过滤器添加到用户认证过滤器之前
        ;
    }

    /**
     * Token 校验过滤器
     * @return
     */
    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter() {
        return new TokenAuthenticationFilter();
    }

}