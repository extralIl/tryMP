package com.extra.demo.mptest.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.extra.demo.mptest.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiweihao
 * @since 2020-05-13
 */
public interface UserMapper extends BaseMapper<User> {
// @Param("user") User user
    IPage<User> selectUsersByPages(Page<User> page, @Param("user") User user, @Param(Constants.WRAPPER) Wrapper<User> wrapper);

}
