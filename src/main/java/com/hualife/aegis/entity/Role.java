package com.hualife.aegis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-19
 */
public class Role extends Model<Role> {

    private static final long serialVersionUID = 1L;

    /**
     * 角色id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "角色Id")
    private Integer id;

    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称", required = true)
    private String name;

    /**
     * 所属系统类型
     */
    @ApiModelProperty(value = "所属系统类型", required = false)
    private Integer systemType;

    /**
     * 类型:1-超级管理员,2-系统管理员,3-业务员
     */
    private Integer type;

    /**
     * 角色状态:1-可用，0-不可用
     */
    private Integer status;

    /**
     * 系统id
     */
    private Integer systemId;
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


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSystemType() {
        return systemType;
    }

    public void setSystemType(Integer systemType) {
        this.systemType = systemType;
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

    public Integer getSystemId() {
        return systemId;
    }

    public void setSystemId(Integer systemId) {
        this.systemId = systemId;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
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
    public String toString() {
        return "Role{" +
        "id=" + id +
        ", name=" + name +
        ", systemType=" + systemType +
        ", type=" + type +
        ", status=" + status +
        ", systemId=" + systemId +
        "}";
    }
}
