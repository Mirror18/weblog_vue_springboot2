package com.mirror.weblog.common.constant;

import java.time.format.DateTimeFormatter;

/**
 * 全局静态变量
 * @author mirror
 */
public interface Constants {
    /**
     * 月-日 格式
     */
    DateTimeFormatter MONTH_DAY_FORMATTER = DateTimeFormatter.ofPattern("MM-dd");
}
