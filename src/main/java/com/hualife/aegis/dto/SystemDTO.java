package com.hualife.aegis.dto;

import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午3:35 2018/9/20
 */
@Data
public class SystemDTO {
    /**
     * 系统id:HUA_APP_000001开始
     */
    private String id;

    /**
     * 系统名称
     */
    private String name;

    /**
     * 系统负责人
     */
    private String principal;


    /**
     * 系统权限管理类型:1:API,2:UI
     */
    private Integer authorityType;

    /**
     * 系统类型:1:系统管理类，2:业务类
     */
    private Integer type;

    /**
     * 是否有效:1-有效 0-无效
     */
    private Integer status;

    /**
     * 每页显示条数，默认 20
     */
    private long size = 20;
    /**
     * 当前页
     */
    private long current = 1;

}
