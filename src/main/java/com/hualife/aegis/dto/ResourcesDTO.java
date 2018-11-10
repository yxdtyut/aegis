package com.hualife.aegis.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午2:51 2018/9/20
 */
@Data
public class ResourcesDTO {
    @ApiModelProperty(value = "系统id",required = true)
    private Integer systemId;
    @ApiModelProperty(value = "资源名称",required = false)
    private String resourcesName;
    @ApiModelProperty(value = "角色ID",required = false)
    private Integer roleId;
}
