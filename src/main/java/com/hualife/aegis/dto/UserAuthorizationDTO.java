package com.hualife.aegis.dto;

import com.hualife.aegis.entity.Organization;
import com.hualife.aegis.entity.Role;
import com.hualife.aegis.entity.System;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author : rongyu
 * @Description :
 * @Date : 下午4:50 2018/9/29
 */
@Data
public class UserAuthorizationDTO {
    /*用户id*/
    @ApiModelProperty(value = "用户id", required = false)
    private Integer userId;

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getUserId() {
        return userId;
    }

    /*系统id集合*/
    @ApiModelProperty(value = "系统id集合", required = false)
    private List<Integer> systemIds;

    public void setSystemIds(List<Integer> systemIds) {
        this.systemIds = systemIds;
    }

    public List<Integer> getSystemIds() {
        return systemIds;
    }

    /*角色id集合*/
    @ApiModelProperty(value = "角色id集合", required = false)
    private List<Integer> roleIds;

    public void setRoleIds(List<Integer> roleIds) {
        this.roleIds = roleIds;
    }

    public List<Integer> getRoleIds() {
        return roleIds;
    }

    /*数据权限集合*/
    @ApiModelProperty(value = "数据权限id集合", required = false)
    private List<Integer> organizationIds;

    public void setOrganizationIds(List<Integer> organizationIds) {
        this.organizationIds = organizationIds;
    }

    public List<Integer> getOrganizationIds() {
        return organizationIds;
    }
}
