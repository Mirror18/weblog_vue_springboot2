package com.mirror.weblog.web.controller;

import com.mirror.weblog.common.aspect.ApiOperationLog;
import com.mirror.weblog.web.model.User;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mirror
 * &#064;description:  TODO
 */
@RestController
@Slf4j
public class TestController {

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public User test(@RequestBody User user) {
//        log.info("当前traceId: {}", MDC.get("traceId"));
        // 返参
        return user;
    }

}
