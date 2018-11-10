package com.hualife.aegis.service;

import com.hualife.aegis.dto.ResourcesLevelDTO;
import com.hualife.aegis.entity.Resources;
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
public interface ResourcesService extends IService<Resources> {
    /**
     * 通过角色id获取资源集合.
     */
    List<Resources> getResourcesByRoleId(Integer roleId);
}
