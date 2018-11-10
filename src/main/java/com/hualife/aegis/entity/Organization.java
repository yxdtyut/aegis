package com.hualife.aegis.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * <p>
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-20
 */
public class Organization extends Model<Organization> {

    private static final long serialVersionUID = 1L;

    /**
     * 机构编码
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 机构全称
     */
    private String fullName;

    /**
     * 机构级别
     */
    private String level;


    /**
     * 上级机构编码
     */
    private Integer parentId;

    /**
     * 是否有效:1-有效 0-无效
     */
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

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
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
        return "Organization{" +
                "id=" + id +
                ", name=" + name +
                ", fullName=" + fullName +
                ", level=" + level +
                ", parentId=" + parentId +
                ", status=" + status +
                "}";
    }
}
