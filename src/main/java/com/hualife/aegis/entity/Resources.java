package com.hualife.aegis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-19
 */
public class Resources extends Model<Resources> {

    private static final long serialVersionUID = 1L;

    /**
     * 资源id
     */
    @ApiModelProperty(value = "资源id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 资源名称
     */
    @ApiModelProperty(value = "资源名称", required = true)
    private String name;

    /**
     * 资源类型:1-菜单，2-按钮，3-超链接
     */
    @ApiModelProperty(value = "资源类型", required = true)
    private Integer type;

    /**
     * 上级资源id
     */
    @ApiModelProperty(value = "上级资源id", required = true)
    private Integer parentId = 0;

    /**
     * 资源级别
     */
    @ApiModelProperty(value = "资源级别", required = false)
    private String level;

    /**
     * 状态:1-可用，0-不可用
     */
    @ApiModelProperty(value = "资源状态", required = false)
    private Integer status = 1;

    /**
     * 顺序
     */
    @ApiModelProperty(value = "资源顺序", required = false)
    private Integer sequence;

    /**
     * 资源路径
     */
    @ApiModelProperty(value = "资源", required = true)
    private String url;

    /**
     * 系统id
     */
    @ApiModelProperty(value = "系统id", required = true)
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
        return "Resources{" +
                "id=" + id +
                ", name=" + name +
                ", type=" + type +
                ", parentId=" + parentId +
                ", level=" + level +
                ", status=" + status +
                ", sequence=" + sequence +
                ", url=" + url +
                ", systemId=" + systemId +
                "}";
    }
}
