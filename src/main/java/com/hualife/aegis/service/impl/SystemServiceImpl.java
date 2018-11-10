package com.hualife.aegis.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hualife.aegis.dto.SystemDTO;
import com.hualife.aegis.dto.UserSystemDTO;
import com.hualife.aegis.entity.System;
import com.hualife.aegis.entity.User;
import com.hualife.aegis.entity.UserSystem;
import com.hualife.aegis.mapper.SystemMapper;
import com.hualife.aegis.service.SystemService;
import com.hualife.aegis.service.UserSystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-20
 */
@Service
public class SystemServiceImpl extends ServiceImpl<SystemMapper, System> implements SystemService {
    @Autowired
    private SystemMapper systemMapper;

    @Autowired
    private UserSystemService userSystemService;

    @Override
    public boolean addAuthorization(UserSystem userSystem) {
        return userSystemService.addByUserIdAndSystemId(userSystem);
    }

    @Override
    public boolean deleteAuthorization(UserSystem userSystem) {
        return userSystemService.deleteByUserIdAndSystemId(userSystem);
    }

    @Override
    public List<System> getSystemByUserId(Integer userId) {
        return systemMapper.getSystemByUserId(userId);
    }

    @Override
    public String getStegoKeyById(String id) {
        return systemMapper.getStegoKeyById(id);
    }

    @Override
    public IPage<User> getAuthorizedUsersPage(Page<User> aPage, UserSystemDTO userSystemDTO) {
        return systemMapper.getAuthorizedUsersPage(aPage, userSystemDTO);
    }

    @Override
    public IPage<User> getNotAuthorizedUsersPage(Page<User> bPage, UserSystemDTO userSystemDTO) {
        return systemMapper.getNotAuthorizedUsersPage(bPage, userSystemDTO);
    }

    @Override
    public IPage<System> getSystemsPage(Page<System> page, SystemDTO systemDTO) {
        return systemMapper.getSystemsPage(page, systemDTO);
    }

    @Override
    public boolean save(System system) {
        return ServiceImpl.retBool(systemMapper.insert(system));
    }

    @Override
    public boolean updateById(System entity) {
        return ServiceImpl.retBool(systemMapper.updateById(entity));
    }

    @Override
    public System getById(Serializable id) {
        return systemMapper.selectById(id);
    }
}
