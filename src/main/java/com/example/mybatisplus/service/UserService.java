package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author clt
 * @since 2024-07-11
 */
public interface UserService extends IService<User> {

    User login(User user);

    User findByPhone(String phone);
}
