package com.hualife.aegis.dto;

import
        com.hualife.aegis.entity.Resources;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : yangxudong
 * @Description :   资源等级dto类
 * @Date : 下午3:34 2018/9/19
 */
@Data
public class ResourcesLevelDTO extends Resources {

    /** 默认当前角色是否拥有此资源权限.*/
    private boolean privilege = false;
    /**
     * 下级资源集合.
     */
    private List<ResourcesLevelDTO> resourcesLevelDTOList = new ArrayList<>();

    public static ResourcesLevelDTO adapt(Resources resources) {
        ResourcesLevelDTO resourcesLevelDTO = new ResourcesLevelDTO();
        BeanUtils.copyProperties(resources,resourcesLevelDTO);
        return resourcesLevelDTO;
    }
}
