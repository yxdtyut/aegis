package com.hualife.aegis.service.impl;

import com.hualife.aegis.entity.Resources;
import com.hualife.aegis.mapper.ResourcesMapper;
import com.hualife.aegis.service.ResourcesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.annotation.PostConstruct;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-19
 */
@Service
public class ResourcesServiceImpl extends ServiceImpl<ResourcesMapper, Resources> implements ResourcesService {

    @Autowired
    private ResourcesMapper resourcesMapper;

    @Override
    public List<Resources> getResourcesByRoleId(Integer roleId) {
        return resourcesMapper.getResourcesByRoleId(roleId);
    }
}
