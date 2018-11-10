package com.hualife.aegis.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : rongyu
 * @Description :
 * @Date : 上午10:15 2018/9/30
 */
@Data
public class UpdateUserDTO {


    /*用户id*/
    @ApiModelProperty(value = "用户id", required = false)
    private Integer userId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /*用户密码*/
    @ApiModelProperty(value = "用户名称", required = false)
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /*用户所属机构id*/
    @ApiModelProperty(value = "用户所属机构id", required = false)
    private Integer organization_id;

    public void setOrganization_id(Integer organization_id) {
        this.organization_id = organization_id;
    }

    public Integer getOrganization_id() {
        return organization_id;
    }
}
