package com.mirror.weblog.admin.service;

import com.mirror.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.mirror.weblog.common.utils.Response;

public interface AdminCategoryService {
    /**
     * 添加分类
     * @param addCategoryReqVO
     * @return
     */
    Response addCategory(AddCategoryReqVO addCategoryReqVO);
}
