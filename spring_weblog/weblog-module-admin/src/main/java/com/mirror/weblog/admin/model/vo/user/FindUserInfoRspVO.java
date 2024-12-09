package com.mirror.weblog.admin.model.vo.user;

import com.mirror.weblog.common.aspect.ApiOperationLog;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mirror
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FindUserInfoRspVO {

    /**
     * 用户名
     */
    private String username;
}
