package com.hualife.aegis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.hualife.aegis.dto.OrganizationDTO;
import com.hualife.aegis.dto.OrganizationLevelDTO;
import com.hualife.aegis.dto.ResourcesDTO;
import com.hualife.aegis.dto.ResourcesLevelDTO;
import com.hualife.aegis.entity.Organization;
import com.hualife.aegis.entity.Resources;
import com.hualife.aegis.service.OrganizationService;
import com.hualife.aegis.service.ResourcesService;
import com.hualife.aegis.service.RoleService;
import com.hualife.aegis.service.TreeService;
import com.hualife.aegis.util.LevelUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午5:41 2018/9/19
 */
@Service
public class TreeServiceImpl implements TreeService {

    @Autowired
    private ResourcesService resourcesService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private OrganizationService organizationService;

    /*user模块根据userId查询相关机构*/
    public List<OrganizationLevelDTO> organizationTreeByUserId(Integer userId) {
        List<Organization> organizationList = organizationService.getOrganizationsByUserId(userId);
        List<OrganizationLevelDTO> organizationLevelDTOList = new ArrayList<>();
        organizationList.stream().forEach((x) -> {
            organizationLevelDTOList.add(OrganizationLevelDTO.adapt(x));
        });
        //** 设置用户对应的数据权限状态.*//*
        if (null != userId) {
            List<Organization> userHasOrganizationsList = organizationService.getOrganizationsByUserId(userId);
            for (OrganizationLevelDTO organizations : organizationLevelDTOList) {
                for (Organization userOrganizations : userHasOrganizationsList) {
                    if (organizations.getId().equals(userOrganizations.getId())) {
                        organizations.setPrivilege(true);
                    }
                }
            }
        }
        return organizationsListToTree(organizationLevelDTOList);
    }

    ;

    /**
     * 角色role模块根据role查询相关的资源resources
     */
    public List<ResourcesLevelDTO> resourcesTreeByRoleId(Integer roleId) {
        List<Resources> resourcesList = resourcesService.getResourcesByRoleId(roleId);
        List<ResourcesLevelDTO> resourcesLevelDTOList = new ArrayList<>();
        resourcesList.stream().forEach((x) -> {
            resourcesLevelDTOList.add(ResourcesLevelDTO.adapt(x));
        });
        //** 设置角色对应的资源状态.*//*
        if (null != roleId) {
            List<Resources> roleHasResourcesList = resourcesService.getResourcesByRoleId(roleId);
            for (ResourcesLevelDTO resources : resourcesLevelDTOList) {
                for (Resources roleResources : roleHasResourcesList) {
                    if (resources.getId().equals(roleResources.getId())) {
                        resources.setPrivilege(true);
                    }
                }
            }
        }
        return resourcesListToTree(resourcesLevelDTOList);
    }

    @Override
    public List<ResourcesLevelDTO> resourcesTree(ResourcesDTO resourcesDTO) {
        QueryWrapper<Resources> wrapper = new QueryWrapper<>();
        wrapper.eq("system_id", resourcesDTO.getSystemId());
        if (null != resourcesDTO.getResourcesName() && StringUtils.isNotBlank(resourcesDTO.getResourcesName())) {
            wrapper.like("name", resourcesDTO.getResourcesName());
        }
        wrapper.eq("status", 1);
        List<Resources> resourcesList = resourcesService.list(wrapper);
        List<ResourcesLevelDTO> resourcesLevelDTOList = new ArrayList<>();
        resourcesList.stream().forEach((x) -> {
            resourcesLevelDTOList.add(ResourcesLevelDTO.adapt(x));
        });
        /** 设置角色对应的资源状态.*/
        if (null != resourcesDTO.getRoleId()) {
            List<Resources> roleHasResourcesList = resourcesService.getResourcesByRoleId(resourcesDTO.getRoleId());
            for (ResourcesLevelDTO resources : resourcesLevelDTOList) {
                for (Resources roleResources : roleHasResourcesList) {
                    if (resources.getId().equals(roleResources.getId())) {
                        resources.setPrivilege(true);
                    }
                }
            }
        }
        return resourcesListToTree(resourcesLevelDTOList);
    }

    /**
     * @param
     * @param resourcesLevelDTOList
     * @Author : yangxudong
     * @Description : 将资源集合dto类转换成树,获取树根
     * @Date : 下午5:46 2018/9/19
     */
    private List<ResourcesLevelDTO> resourcesListToTree(List<ResourcesLevelDTO> resourcesLevelDTOList) {
        Multimap<String, ResourcesLevelDTO> resourcesLevelDTOArrayListMultimap = ArrayListMultimap.create();
        List<ResourcesLevelDTO> rootList = new ArrayList<>();
        resourcesLevelDTOList.stream().forEach((x) -> {
            resourcesLevelDTOArrayListMultimap.put(x.getLevel(), x);
            if (LevelUtil.ROOT.equals(x.getLevel())) {
                rootList.add(x);
            }
        });
        //按照顺序从小到大排序
        Collections.sort(rootList, (s1, s2) -> s1.getSequence() - s2.getSequence());
        // 递归生成树
        transformResourcesTree(rootList, LevelUtil.ROOT, resourcesLevelDTOArrayListMultimap);
        return rootList;
    }

    /**
     * @param rootList
     * @param level
     * @param resourcesLevelDTOArrayListMultimap
     * @Author : yangxudong
     * @Description : 递归生成树
     * @Date : 下午6:02 2018/9/19
     */
    private void transformResourcesTree(List<ResourcesLevelDTO> rootList, String level, Multimap<String, ResourcesLevelDTO> resourcesLevelDTOArrayListMultimap) {
        rootList.stream().forEach((x) -> {
            //获取当前元素下一层
            String nextLevel = LevelUtil.calculateLevel(level, x.getId());
            //处理下一层
            List<ResourcesLevelDTO> nextResourcesDtoList = (List<ResourcesLevelDTO>) resourcesLevelDTOArrayListMultimap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(nextResourcesDtoList)) {
                //排序
                Collections.sort(nextResourcesDtoList, (s1, s2) -> s1.getSequence() - s2.getSequence());
                //设置下一层部门
                x.setResourcesLevelDTOList(nextResourcesDtoList);
                //处理下一层
                transformResourcesTree(nextResourcesDtoList, nextLevel, resourcesLevelDTOArrayListMultimap);
            }
        });
    }

    @Override
    public List<OrganizationLevelDTO> organizationTree(OrganizationDTO organizationDTO) {
        QueryWrapper<Organization> wrapper = new QueryWrapper<>();
        wrapper.eq("status", 1);
        List<Organization> organizationList = organizationService.list(wrapper);
        List<OrganizationLevelDTO> organizationLevelDTOList = new ArrayList<>();
        organizationList.stream().forEach((x) -> {
            organizationLevelDTOList.add(OrganizationLevelDTO.adapt(x));
        });
        /** 设置用户对应的对应的机构状态.*/
        if (null != organizationDTO && null != organizationDTO.getUserId()) {
            List<Organization> userHasOrganizationsList = organizationService.getOrganizationsByUserId(organizationDTO.getUserId());
            for (OrganizationLevelDTO organizations : organizationLevelDTOList) {
                for (Organization userOrganizations : userHasOrganizationsList) {
                    if (organizations.getId().equals(userOrganizations.getId())) {
                        organizations.setPrivilege(true);
                    }
                }
            }
        }
        return organizationsListToTree(organizationLevelDTOList);
    }

    private List<OrganizationLevelDTO> organizationsListToTree(List<OrganizationLevelDTO> organizationLevelDTOList) {
        Multimap<Integer, OrganizationLevelDTO> organizationLevelDTOArrayListMultimap = ArrayListMultimap.create();
        List<OrganizationLevelDTO> rootList = new ArrayList<>();
        /** 找到根层，既最小的level*/
        String minLevel = "";
        Optional<OrganizationLevelDTO> optional = organizationLevelDTOList.stream().min(Comparator.comparing(o -> new Integer(o.getLevel()))
        );
        if (optional.isPresent()) {
            OrganizationLevelDTO minLevelOrganizationLevelDTO = optional.get();
            minLevel = minLevelOrganizationLevelDTO.getLevel();
        }
        for (OrganizationLevelDTO x : organizationLevelDTOList) {
            organizationLevelDTOArrayListMultimap.put(x.getParentId(), x);
            if (minLevel.equals(x.getLevel())) {
                rootList.add(x);
            }
        }
        //按照顺序从小到大排序
        Collections.sort(rootList, (s1, s2) -> s1.getId().compareToIgnoreCase(s2.getId()));
        // 递归生成树
        transformOrganizationsTree(rootList, organizationLevelDTOArrayListMultimap);
        return rootList;
    }

    private void transformOrganizationsTree(List<OrganizationLevelDTO> rootList, Multimap<Integer, OrganizationLevelDTO> organizationLevelDTOArrayListMultimap) {
        rootList.stream().forEach((x) -> {
            //处理下一层
            List<OrganizationLevelDTO> nextOrganizationLevelDTOList = (List<OrganizationLevelDTO>) organizationLevelDTOArrayListMultimap.get(Integer.valueOf(x.getId()));
            if (CollectionUtils.isNotEmpty(nextOrganizationLevelDTOList)) {
                //排序
                Collections.sort(nextOrganizationLevelDTOList, (s1, s2) -> s1.getId().compareToIgnoreCase(s2.getId()));
                //设置下一层部门
                x.setOrganizationLevelTreeList(nextOrganizationLevelDTOList);
                //处理下一层
                transformOrganizationsTree(nextOrganizationLevelDTOList, organizationLevelDTOArrayListMultimap);
            }
        });
    }
}
