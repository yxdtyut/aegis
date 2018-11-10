package com.hualife.aegis.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author : zhaowenhao
 * @Description :
 * @Date : 下午2:51 2018/9/20
 */
@Data
public class OrganizationDTO {
    /**
     * 机构编码
     */
    @ApiModelProperty(value = "机构id", required = false)
    private String id;

    /**
     * 机构名称
     */
    @ApiModelProperty(value = "机构名称", required = false)
    private String name;

    /**
     * 机构全称
     */
    @ApiModelProperty(value = "系统id", required = false)
    private String fullName;

    /**
     * 机构级别
     */
    @ApiModelProperty(value = "机构全称", required = false)
    private String level;


    /**
     * 上级机构编码
     */
    @ApiModelProperty(value = "上级机构编码", required = false)
    private Integer parentId;

    /**
     * 用户id
     */
    @ApiModelProperty(value = "用户id", required = false)
    private Integer userId;

}
