package com.mirror.weblog.admin.service;

import com.mirror.weblog.admin.model.vo.blogsettings.UpdateBlogSettingsReqVO;
import com.mirror.weblog.common.utils.Response;

/**
 * @author mirror
 */
public interface AdminBlogSettingsService {
    /**
     * 更新博客设置信息
     * @param updateBlogSettingsReqVO
     * @return
     */
    Response updateBlogSettings(UpdateBlogSettingsReqVO updateBlogSettingsReqVO);

    /**
     * 获取博客设置详情
     * @return
     */
    Response findDetail();
}
