package com.hualife.aegis.dto;

import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 上午9:56 2018/9/21
 */
@Data
public class UserSystemDTO {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 系统id
     */
    private Integer systemId;

    /**
     * 登陆账号
     */
    private String loginName;

    /**
     * 用户名称
     */
    private String username;

    /**
     * a页面每页显示条数，默认 5
     */
    private long aSize = 5;

    /**
     * a页面当前页
     */
    private long aCurrent = 1;

    /**
     * b页面每页显示条数，默认 5
     */
    private long bSize = 5;

    /**
     * b页面当前页
     */
    private long bCurrent = 1;
}
