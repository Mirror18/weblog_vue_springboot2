package com.mirror.weblog.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author mirror
 */
@Configuration
@MapperScan("com.mirror.weblog.common.domain.mapper")
public class MybatisPlusConfig {
}
