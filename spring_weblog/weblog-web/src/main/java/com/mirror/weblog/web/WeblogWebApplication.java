package com.mirror.weblog.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
 * @author mirror
 */
@SpringBootApplication
//多模块项目中，必须手动指定扫描
@ComponentScan({"com.mirror.weblog.*"})
@EnableScheduling // 启用定时任务
public class WeblogWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeblogWebApplication.class,args);
    }
}
