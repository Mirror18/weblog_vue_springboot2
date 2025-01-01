package com.mirror.weblog.admin.service;

import com.mirror.weblog.admin.model.vo.user.UpdateAdminUserPasswordReqVO;
import com.mirror.weblog.common.utils.Response;

/**
 * @author mirror
 */
public interface AdminUserService {
    /**
     * 修改密码
     * @param updateAdminUserPasswordReqVO
     * @return
     */
    Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO);

    /**
     * 获取当前登录用户信息
     * @return
     */
    Response findUserInfo();
}