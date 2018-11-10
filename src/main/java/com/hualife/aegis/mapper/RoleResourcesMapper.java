package com.hualife.aegis.mapper;

import com.hualife.aegis.entity.RoleResources;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-18
 */
public interface RoleResourcesMapper extends BaseMapper<RoleResources> {
    //  角色和资源绑定保存至role_resources数据库表
    void saveResourcesByrole(RoleResources roleResources);
}
