package com.hualife.aegis.service;

import com.hualife.aegis.entity.Organization;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-20
 */
public interface OrganizationService extends IService<Organization> {

    List<Organization> getOrganizationsByUserId(Integer userId);
}
