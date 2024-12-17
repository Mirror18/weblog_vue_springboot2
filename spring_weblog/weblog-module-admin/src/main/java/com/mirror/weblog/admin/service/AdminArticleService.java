package com.mirror.weblog.admin.service;

import com.mirror.weblog.admin.model.vo.article.PublishArticleReqVO;
import com.mirror.weblog.common.utils.Response;

public interface AdminArticleService {
    /**
     * 发布文章
     * @param publishArticleReqVO
     * @return
     */
    Response publishArticle(PublishArticleReqVO publishArticleReqVO);
}

