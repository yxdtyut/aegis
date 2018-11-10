package com.hualife.aegis.dto;

import com.hualife.aegis.entity.Organization;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午4:00 2018/9/20
 */
@Data
public class OrganizationLevelDTO extends Organization {
    /**
     * 默认当前角色是否拥有此资源权限.
     */
    private boolean privilege = false;
    /**
     * 下级机构集合.
     */
    private List<OrganizationLevelDTO> organizationLevelTreeList = new ArrayList<>();

    public static OrganizationLevelDTO adapt(Organization organization) {
        OrganizationLevelDTO organizationLevelDTO = new OrganizationLevelDTO();
        BeanUtils.copyProperties(organization, organizationLevelDTO);
        return organizationLevelDTO;
    }
}
