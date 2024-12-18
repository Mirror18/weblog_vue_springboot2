package com.mirror.weblog.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mirror.weblog.admin.model.vo.tag.*;
import com.mirror.weblog.admin.service.AdminTagService;
import com.mirror.weblog.common.domain.dos.ArticleTagRelDO;
import com.mirror.weblog.common.domain.dos.TagDO;
import com.mirror.weblog.common.domain.mapper.ArticleTagRelMapper;
import com.mirror.weblog.common.domain.mapper.TagMapper;
import com.mirror.weblog.common.enums.ResponseCodeEnum;
import com.mirror.weblog.common.exception.BizException;
import com.mirror.weblog.common.model.vo.SelectRspVO;
import com.mirror.weblog.common.utils.PageResponse;
import com.mirror.weblog.common.utils.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author mirror
 */
@Service
@Slf4j
public class AdminTagServiceImpl extends ServiceImpl<TagMapper, TagDO> implements AdminTagService {

    @Autowired
    private TagMapper tagMapper;

    /**
     * 添加分类
     *
     * @param addTagReqVO
     * @return
     */
    @Override
    public Response addTag(AddTagReqVO addTagReqVO) {

        //vo转DO
        List<String> tags = addTagReqVO.getTags();
        List<TagDO> tagDOS = tags.stream()
                .map(tag -> TagDO.builder()
                        .name(tag.trim())
                        .createTime(LocalDateTime.now())
                        .updateTime(LocalDateTime.now())
                        .build())
                .collect(Collectors.toList());
        //批量插入
        try {
            saveBatch(tagDOS);
        } catch (Exception e) {
            log.warn("该标签已存在", e);
        }
        return Response.success();
    }


    @Override
    public PageResponse findTagPageList(FindTagPageListReqVO findTagPageListReqVO) {
        // 获取当前页、以及每页需要展示的数据数量
        Long current = findTagPageListReqVO.getCurrent();
        Long size = findTagPageListReqVO.getSize();


        String name = findTagPageListReqVO.getName();
        LocalDate startDate = findTagPageListReqVO.getStartDate();
        LocalDate endDate = findTagPageListReqVO.getEndDate();


        // 执行分页查询
        Page<TagDO> tagDOPage = tagMapper.selectPageList(current, size, name, startDate, endDate);

        List<TagDO> tagDOS = tagDOPage.getRecords();

        // DO 转 VO
        List<FindTagPageListRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
            vos = tagDOS.stream()
                    .map(tagDO -> FindTagPageListRspVO.builder()
                            .id(tagDO.getId())
                            .name(tagDO.getName())
                            .createTime(tagDO.getCreateTime())
                            .build())
                    .collect(Collectors.toList());
        }

        return PageResponse.success(tagDOPage, vos);
    }

//    @Override
//    public Response deleteTag(DeleteTagReqVO deleteTagReqVO) {
//        // 标签 ID
//        Long tagId = deleteTagReqVO.getId();
//
//        // 删除标签
//        int count = tagMapper.deleteById(tagId);
//
//        return count == 1 ? Response.success() : Response.fail(ResponseCodeEnum.TAG_NOT_EXISTED);
//    }

    @Autowired
    private ArticleTagRelMapper articleTagRelMapper;

    /**
     * 删除标签
     *
     * @param deleteTagReqVO
     * @return
     */
    @Override
    public Response deleteTag(DeleteTagReqVO deleteTagReqVO) {
        // 标签 ID
        Long tagId = deleteTagReqVO.getId();

        // 校验该标签下是否有关联的文章，若有，则不允许删除，提示用户需要先删除标签下的文章
        ArticleTagRelDO articleTagRelDO = articleTagRelMapper.selectOneByTagId(tagId);

        if (Objects.nonNull(articleTagRelDO)) {
            log.warn("==> 此标签下包含文章，无法删除，tagId: {}", tagId);
            throw new BizException(ResponseCodeEnum.TAG_CAN_NOT_DELETE);
        }

        // 根据标签 ID 删除
        int count = tagMapper.deleteById(tagId);

        return count == 1 ? Response.success() : Response.fail(ResponseCodeEnum.TAG_NOT_EXISTED);
    }
    @Override
    public Response searchTag(SearchTagReqVO searchTagReqVO) {
        String key = searchTagReqVO.getKey();
        List<TagDO> tagDOS = tagMapper.selectByKey(key);
        List<SelectRspVO> searchTagRspVOS = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
             searchTagRspVOS= tagDOS.stream()
                    .map(tagDO -> SelectRspVO.builder()
                            .value(tagDO.getId())
                            .label(tagDO.getName())
                            .build())
                    .collect(Collectors.toList());
        }
        return Response.success(searchTagRspVOS);

    }


    @Override
    public Response findTagSelectList() {
        // 查询所有标签, Wrappers.emptyWrapper() 表示查询条件为空
        List<TagDO> tagDOS = tagMapper.selectList(Wrappers.emptyWrapper());

        // DO 转 VO
        List<SelectRspVO> vos = null;
        if (!CollectionUtils.isEmpty(tagDOS)) {
            vos = tagDOS.stream()
                    .map(tagDO -> SelectRspVO.builder()
                            .label(tagDO.getName())
                            .value(tagDO.getId())
                            .build())
                    .collect(Collectors.toList());
        }

        return Response.success(vos);
    }
}
