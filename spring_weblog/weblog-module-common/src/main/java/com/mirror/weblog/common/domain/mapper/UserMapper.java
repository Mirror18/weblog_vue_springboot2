package com.mirror.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mirror.weblog.common.domain.dos.UserDO;

/**
 * @author mirror
 */
public interface UserMapper extends BaseMapper<UserDO> {
    /**
     * 根据用户名查询信息的默认方法
     * @param username
     * @return
     */
    default UserDO findByUsername(String username) {
        LambdaQueryWrapper<UserDO> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserDO::getUsername, username);
        return selectOne(wrapper);
    }
}
