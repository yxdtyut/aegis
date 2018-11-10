package com.hualife.aegis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-20
 */
public class System extends Model<System> {

    private static final long serialVersionUID = 1L;

    /**
     * 系统id:HUA_APP_000001开始
     */
    @ApiModelProperty(value = "系统id")
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 系统名称
     */
    @ApiModelProperty(value = "系统名称", required = true)
    private String name;

    /**
     * 系统负责人
     */
    @ApiModelProperty(value = "系统负责人", required = true)
    private String principal;

    /**
     * 负责人手机
     */
    @ApiModelProperty(value = "负责人手机", required = true)
    private String telephone;

    /**
     * 负责人邮箱
     */
    @ApiModelProperty(value = "负责人邮箱", required = true)
    private String email;

    /**
     * 秘钥
     */
    @ApiModelProperty(value = "系统秘钥", required = false)
    private String stegoKey;

    /**
     * 系统权限管理类型:1:API,2:UI
     */
    @ApiModelProperty(value = "系统权限管理类型", required = true)
    private Integer authorityType;

    /**
     * 系统类型:1:系统管理类，2:业务类
     */
    @ApiModelProperty(value = "系统类型", required = true)
    private Integer type;

    /**
     * 是否有效:1-有效 0-无效
     */
    @ApiModelProperty(value = "是否有效", required = false)
    private Integer status;
    /*
     * 创建人
     */
    private String createdUser;

    /**
     * 创建时间.
     */
    private Date createdDate;

    /*
     * 更新人
     */
    private String updatedUser;

    /**
     * 更新时间.
     */
    private Date updatedDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStegoKey() {
        return stegoKey;
    }

    public void setStegoKey(String stegoKey) {
        this.stegoKey = stegoKey;
    }

    public Integer getAuthorityType() {
        return authorityType;
    }

    public void setAuthorityType(Integer authorityType) {
        this.authorityType = authorityType;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser(String createdUser) {
        this.createdUser = createdUser;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedUser() {
        return updatedUser;
    }

    public void setUpdatedUser(String updatedUser) {
        this.updatedUser = updatedUser;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "System{" +
                "id=" + id +
                ", name=" + name +
                ", principal=" + principal +
                ", telephone=" + telephone +
                ", email=" + email +
                ", stegoKey=" + stegoKey +
                ", authorityType=" + authorityType +
                ", type=" + type +
                ", status=" + status +
                "}";
    }
}
