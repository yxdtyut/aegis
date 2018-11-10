package com.hualife.aegis.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hualife.aegis.dto.SystemDTO;
import com.hualife.aegis.dto.UserSystemDTO;
import com.hualife.aegis.entity.System;
import com.hualife.aegis.entity.User;
import com.hualife.aegis.entity.UserSystem;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-20
 */
public interface SystemService extends IService<System> {

    boolean addAuthorization(UserSystem userSystem);

    boolean deleteAuthorization(UserSystem userSystem);

    List<System> getSystemByUserId(Integer userId);

    String getStegoKeyById(String id);

    IPage<User> getAuthorizedUsersPage(Page<User> aPage, UserSystemDTO userSystemDTO);

    IPage<User> getNotAuthorizedUsersPage(Page<User> bPage, UserSystemDTO userSystemDTO);

    IPage<System> getSystemsPage(Page<System> page, SystemDTO systemDTO);
}
