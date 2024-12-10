package com.mirror.weblog.admin.controller;

import com.mirror.weblog.admin.model.vo.category.AddCategoryReqVO;
import com.mirror.weblog.admin.model.vo.category.DeleteCategoryReqVO;
import com.mirror.weblog.admin.service.AdminCategoryService;
import com.mirror.weblog.common.aspect.ApiOperationLog;
import com.mirror.weblog.common.modeL.FindCategoryPageListReqVO;
import com.mirror.weblog.common.utils.PageResponse;
import com.mirror.weblog.common.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
//@Api(tags = "Admin 分类模块")
public class AdminCategoryController {

    @Autowired
    private AdminCategoryService categoryService;

    @PostMapping("/category/add")
//    @ApiOperation(value = "添加分类")
    @ApiOperationLog(description = "添加分类")
    public Response addCategory(@RequestBody @Validated AddCategoryReqVO addCategoryReqVO) {
        return categoryService.addCategory(addCategoryReqVO);
    }

    @PostMapping("/category/list")
//    @ApiOperation(value = "分类分页数据获取")
    @ApiOperationLog(description = "分类分页数据获取")
    public PageResponse findCategoryList(@RequestBody @Validated FindCategoryPageListReqVO findCategoryPageListReqVO) {
        return categoryService.findCategoryList(findCategoryPageListReqVO);
    }

    @PostMapping("/category/delete")
//    @ApiOperation(value = "删除分类")
    @ApiOperationLog(description = "删除分类")
    public Response deleteCategory(@RequestBody @Validated DeleteCategoryReqVO deleteCategoryReqVO) {
        return categoryService.deleteCategory(deleteCategoryReqVO);
    }

}
