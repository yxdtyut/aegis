package com.hualife.aegis.service.impl;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleCreateTableStatement;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hualife.aegis.common.CodeMsg;
import com.hualife.aegis.dto.OrganizationLevelDTO;
import com.hualife.aegis.dto.ResourcesLevelDTO;
import com.hualife.aegis.entity.*;
import com.hualife.aegis.mapper.OrganizationMapper;
import com.hualife.aegis.mapper.ResourcesMapper;
import com.hualife.aegis.mapper.RoleMapper;
import com.hualife.aegis.mapper.RoleResourcesMapper;
import com.hualife.aegis.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hualife.merlin.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Autowired
    private RoleResourcesMapper roleResourcesMapper;

    /*角色和资源绑定保存*/
    public void saveResourcesByrole(Integer roleId, List<Integer> resourcesIds) {

        for (Integer resourcesId : resourcesIds) {
            RoleResources roleResources = new RoleResources();
            roleResources.setRoleId(roleId);
            roleResources.setResourcesId(resourcesId);
            roleResourcesMapper.insert(roleResources);
        }
    }
}
