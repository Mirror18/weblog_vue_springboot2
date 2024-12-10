package com.mirror.weblog.admin.service;

import com.mirror.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.mirror.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.mirror.weblog.common.modeL.FindCategoryPageListReqVO;
import com.mirror.weblog.common.utils.PageResponse;
import com.mirror.weblog.common.utils.Response;

public interface AdminCategoryService {
    /**
     * 添加分类
     * @param addCategoryReqVO
     * @return
     */
    Response addCategory(AddCategoryReqVO addCategoryReqVO);

    /**
     * 分类分页数据查询
     * @param findCategoryPageListReqVO
     * @return
     */
    PageResponse findCategoryList(FindCategoryPageListReqVO findCategoryPageListReqVO);

    /**
     * 删除分类
     * @param deleteCategoryReqVO
     * @return
     */
    Response deleteCategory(DeleteCategoryReqVO deleteCategoryReqVO);
}
