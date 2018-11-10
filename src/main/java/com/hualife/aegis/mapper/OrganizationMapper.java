package com.hualife.aegis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hualife.aegis.entity.Organization;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-20
 */
public interface OrganizationMapper extends BaseMapper<Organization> {
    List<Organization> getOrganizationsByUserId(@Param("userId") Integer userId);
}
