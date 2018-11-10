package com.hualife.aegis.common;

import lombok.Getter;

/**
 * @Author : yangxudong
 * @Description :   错误码
 * @Date : 下午3:04 2018/9/21
 */
@Getter
public class CodeMsg {

    /**
     * 通用模块.
     */
    public static final CodeMsg OPERATION_FAILED = new CodeMsg("500", "操作失败");

    /**
     * 登陆模块.
     */
    public static final CodeMsg USER_NOT_LOGIN = new CodeMsg("401", "用户未登陆");
    public static final CodeMsg SESSION_INVALIDE = new CodeMsg("402", "会话失效");
    public static final CodeMsg DONT_HAVE_AUTHORIZE = new CodeMsg("403", "抱歉您无此权限");

    /**
     * 用户模块.5001xx
     */
    public static final CodeMsg USER_NOT_EXIST = new CodeMsg("500101", "用户不存在");
    public static final CodeMsg PWS_NOT_EXIST = new CodeMsg("500102", "密码不正确");

    /**
     * 资源模块.5002xx
     */
    public static final CodeMsg RESOURCES_NOT_EXIST = new CodeMsg("500201", "资源不存在");
    /**
     * 角色模块.5003xx
     */
    public static final CodeMsg ROLE_NOT_EXIST = new CodeMsg("500301", "角色不存在");
    /**
     * 机构模块.5004xx
     */
    public static final CodeMsg ORGANIZATION_NOT_EXIST = new CodeMsg("500401", "机构不存在");
    /**
     * 系统模块.5005xx
     */
    public static final CodeMsg SYSTEM_NOT_EXIST = new CodeMsg("500501", "系统不存在");
    public static final CodeMsg SYSTEM_AUTHORIZE_REPEATED = new CodeMsg("500502", "系统授权重复");
    public static final CodeMsg SYSTEM_AUTHORIZE_NOT_EXIST = new CodeMsg("500503", "系统授权不存在");


    private String code;
    private String msg;

    private CodeMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    private CodeMsg() {
    }

}
