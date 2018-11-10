package com.hualife.aegis.dto;

import lombok.Data;

/**
 * @Author : yangxudong
 * @Description :
 * @Date : 下午3:46 2018/9/19
 */
@Data
public class LoginDTO {
    private String loginName;
    private String password;
    private String ipAddress;
    private String browser;
}
