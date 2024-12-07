package com.mirror.weblog.admin.service;

import com.mirror.weblog.admin.model.vo.UpdateAdminUserPasswordReqVO;
import com.mirror.weblog.common.utils.Response;

/**
 * @author mirror
 */
public interface AdminUserService {
    Response updatePassword(UpdateAdminUserPasswordReqVO updateAdminUserPasswordReqVO);
}
