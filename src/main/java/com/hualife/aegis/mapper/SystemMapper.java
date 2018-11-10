package com.hualife.aegis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hualife.aegis.dto.SystemDTO;
import com.hualife.aegis.dto.UserSystemDTO;
import com.hualife.aegis.entity.System;
import com.hualife.aegis.entity.User;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-20
 */
public interface SystemMapper extends BaseMapper<System> {

    /**
     * <p>
     * 插入一条记录
     * </p>
     *
     * @param system 实体对象
     */
    int insert(System system);

    /**
     * <p>
     * 根据 ID 修改
     * </p>
     *
     * @param system 实体对象
     */
    int updateById(System system);

    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param id 主键ID
     */
    System selectById(Serializable id);

    List<System> getSystemByUserId(Integer userId);

    String getStegoKeyById(String id);

    IPage<User> getAuthorizedUsersPage(Page<User> aPage, @Param("userSystemDTO") UserSystemDTO userSystemDTO);

    IPage<User> getNotAuthorizedUsersPage(Page<User> bPage, @Param("userSystemDTO") UserSystemDTO userSystemDTO);

    IPage<System> getSystemsPage(Page<System> page, @Param("systemDTO") SystemDTO systemDTO);
}
