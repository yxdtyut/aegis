package com.hualife.aegis.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.omg.CORBA.PRIVATE_MEMBER;

import java.util.List;

/**
 * @Author : rongyu
 * @Description :   资源等级dto类
 * @Date : 上午11:25 2018/9/30
 */
@Data
public class SaveResourcesAndRoleDTO {
    public Integer getRoleId() {
        return roleId;
    }

    /*角色id*/
    @ApiModelProperty(value = "角色id", required = false)
    private Integer roleId;

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getResourcesIds() {
        return resourcesIds;
    }

    public void setResourcesIds(List<Integer> resourcesIds) {
        this.resourcesIds = resourcesIds;
    }

    /*资源id*/
    @ApiModelProperty(value = "资源id", required = false)
    private List<Integer> resourcesIds;
}
