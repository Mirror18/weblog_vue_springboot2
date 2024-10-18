package com.mirror.weblog.web.controller;

import com.mirror.weblog.common.aspect.ApiOperationLog;
import com.mirror.weblog.web.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.FieldError;
import java.util.stream.Collectors;

/**
 * @author mirror
 * &#064;description:  TODO
 */
@RestController
@Slf4j
public class TestController {

//    @PostMapping("/test")
//    @ApiOperationLog(description = "测试接口")
//    public User test(@RequestBody User user) {
////        log.info("当前traceId: {}", MDC.get("traceId"));
//        // 返参
//        return user;
//    }

    @PostMapping("/test")
    @ApiOperationLog(description = "测试接口")
    public ResponseEntity<String> test(@RequestBody @Validated User user, BindingResult bindingResult) {
        // 是否存在校验错误
        if (bindingResult.hasErrors()) {
            // 获取校验不通过字段的提示信息
            String errorMsg = bindingResult.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.joining(", "));

            return ResponseEntity.badRequest().body(errorMsg);
        }

        // 返参
        return ResponseEntity.ok("参数没有任何问题");
    }
}
