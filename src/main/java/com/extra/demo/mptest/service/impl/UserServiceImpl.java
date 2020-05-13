package com.extra.demo.mptest.service.impl;

import com.extra.demo.mptest.entity.User;
import com.extra.demo.mptest.mapper.UserMapper;
import com.extra.demo.mptest.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiweihao
 * @since 2020-05-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
