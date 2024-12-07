package com.mirror.weblog.common.domain.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mirror.weblog.common.domain.dos.UserDO;

import java.time.LocalDateTime;

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

    /**
     * 根据用户名更新密码和更新时间
     * @param username
     * @param password
     * @return
     */
    default int updatePasswordByUsername(String username, String password) {
        LambdaUpdateWrapper<UserDO> wrapper = new LambdaUpdateWrapper<>();
        // 设置要更新的字段
        wrapper.set(UserDO::getPassword, password);
        wrapper.set(UserDO::getUpdateTime, LocalDateTime.now());
        // 更新条件
        wrapper.eq(UserDO::getUsername, username);

        return update(null, wrapper);
    }

}
