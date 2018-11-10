package com.hualife.aegis.dto;

import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 上午9:56 2018/9/21
 */
@Data
public class UserDTO {
    /**
     * 登陆账号
     */
    private String loginName;

    /**
     * 用户名称
     */
    private String name;
    /**
     * 所属机构id
     */
    private Integer organizationId;
    /**
     * 每页显示条数，默认 20
     */
    private long size = 20;
    /**
     * 当前页
     */
    private long current = 1;
}
