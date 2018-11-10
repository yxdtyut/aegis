package com.hualife.aegis.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hualife.aegis.entity.Organization;
import com.hualife.aegis.mapper.OrganizationMapper;
import com.hualife.aegis.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {
    @Autowired
    private OrganizationMapper organizationMapper;

    @Override
    public List<Organization> getOrganizationsByUserId(Integer userId) {
        return organizationMapper.getOrganizationsByUserId(userId);
    }
}