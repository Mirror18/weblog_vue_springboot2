package com.mirror.weblog.admin.service;

import com.mirror.weblog.admin.model.vo.user.UpdateAdminUserPasswordReqVO;
import com.mirror.weblog.common.utils.Response;

/**
 * @author mirror
 */
public interface AdminUserService {
    Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO);

    /**
     * 获取当前登录用户信息
     *
     */
    Response findUserInfo();
}
