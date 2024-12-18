package com.mirror.weblog.common.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author mirror
 */
public interface InsertBatchMapper<T> extends BaseMapper<T> {

    // 批量插入
    void insertBatchSomeColumn(@Param("list") List<T> batchList);

}

