package com.hualife.aegis.mapper;

import com.hualife.aegis.entity.Resources;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-19
 */
public interface ResourcesMapper extends BaseMapper<Resources> {
    List<Resources> getResourcesByRoleId(Integer roleId);
}
