package com.hualife.aegis.mapper;

import com.hualife.aegis.entity.User;
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
public interface UserMapper extends BaseMapper<User> {

    List<String> findUrlListByUserId(@Param("id") Integer id);
}
