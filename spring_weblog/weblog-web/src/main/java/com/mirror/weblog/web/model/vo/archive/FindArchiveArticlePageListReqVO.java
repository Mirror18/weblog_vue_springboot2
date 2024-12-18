package com.mirror.weblog.web.model.vo.archive;

import com.mirror.weblog.common.model.BasePageQuery;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
//@ApiModel(value = "文章归档分页 VO")
public class FindArchiveArticlePageListReqVO extends BasePageQuery {
}
