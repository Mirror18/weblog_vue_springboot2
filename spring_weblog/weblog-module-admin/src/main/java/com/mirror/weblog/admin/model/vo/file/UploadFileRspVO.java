package com.mirror.weblog.admin.model.vo.file;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author mirror
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UploadFileRspVO {

    /**
     * 文件的访问链接
     */
    private String url;

}
