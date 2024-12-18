package com.mirror.weblog.admin.service;

import com.mirror.weblog.admin.model.vo.tag.AddTagReqVO;
import com.mirror.weblog.admin.model.vo.tag.DeleteTagReqVO;
import com.mirror.weblog.admin.model.vo.tag.FindTagPageListReqVO;
import com.mirror.weblog.admin.model.vo.tag.SearchTagReqVO;
import com.mirror.weblog.common.utils.PageResponse;
import com.mirror.weblog.common.utils.Response;

/**
 * @author mirror
 */
public interface AdminTagService {
    /**
     * 添加标签集合
     * @param addTagReqVO
     * @return
     */
    Response addTag(AddTagReqVO addTagReqVO);

    /**
     * 标签分页数据查询
     * @param findTagPageListReqVO
     * @return
     */
    PageResponse findTagPageList(FindTagPageListReqVO findTagPageListReqVO);

    /**
     * 删除标签
     * @param deleteTagReqVO
     * @return
     */
    Response deleteTag(DeleteTagReqVO deleteTagReqVO);

    /**
     * 根据标签关键词模糊查询
     * @param searchTagReqVO
     * @return
     */
    Response searchTag(SearchTagReqVO searchTagReqVO);

    /**
     * 查询标签 Select 列表数据
     * @return
     */
    Response findTagSelectList();

}
