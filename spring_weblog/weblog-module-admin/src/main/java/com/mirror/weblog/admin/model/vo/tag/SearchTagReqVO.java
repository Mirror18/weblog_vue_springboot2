package com.mirror.weblog.admin.model.vo.tag;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author mirror
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@ApiModel(value = "标签模糊查询 VO")
public class SearchTagReqVO {
    @NotBlank(message = "标签查询关键词不能为空")
    private String  key;

}
