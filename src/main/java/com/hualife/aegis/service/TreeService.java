package com.hualife.aegis.service;

import com.hualife.aegis.dto.OrganizationDTO;
import com.hualife.aegis.dto.OrganizationLevelDTO;
import com.hualife.aegis.dto.ResourcesDTO;
import com.hualife.aegis.dto.ResourcesLevelDTO;

import java.util.List;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午3:33 2018/9/19
 */

public interface TreeService {
    List<ResourcesLevelDTO> resourcesTree(ResourcesDTO resourcesDTO);
    List<ResourcesLevelDTO> resourcesTreeByRoleId(Integer id);
    List<OrganizationLevelDTO> organizationTree(OrganizationDTO organizationDTO);
    List<OrganizationLevelDTO> organizationTreeByUserId(Integer userId);
}
