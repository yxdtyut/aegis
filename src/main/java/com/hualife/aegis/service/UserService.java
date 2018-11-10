package com.hualife.aegis.service;

import com.hualife.aegis.entity.System;
import com.hualife.aegis.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-19
 */
public interface UserService extends IService<User> {

    List<String> findUrlListByUserId(Integer id);

    //    逻辑删除用户
    void deleteUser(Integer userId);

    /*用户授权根据用户id保存系统*/
    void saveSystemByUserId(Integer userId, List<Integer> systemIds);

    /*用户授权根据用户id保存角色*/
    void saveRoleByUserId(Integer userId, List<Integer> roleIds);

    /*用户授权根据用户id保存数据权限*/
    void saveOriganizationByUserId(Integer userId, List<Integer> origanizationIds);
}
