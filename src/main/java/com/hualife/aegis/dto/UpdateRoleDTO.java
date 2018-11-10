package com.hualife.aegis.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : rongyu
 * @Description :
 * @Date :上午9:50 2018/9/30
 */
@Data
public class UpdateRoleDTO {
    /*角色id*/
    @ApiModelProperty(value = "角色id", required = false)
    private Integer roleId;

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    /*角色新的名字*/
    @ApiModelProperty(value = "角色名字", required = false)
    private String newRoleName;

    public void setNewRoleName(String newRoleName) {
        this.newRoleName = newRoleName;
    }

    public String getNewRoleName() {
        return newRoleName;
    }
}
