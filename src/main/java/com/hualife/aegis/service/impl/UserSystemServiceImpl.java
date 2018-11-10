package com.hualife.aegis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hualife.aegis.common.CodeMsg;
import com.hualife.aegis.entity.UserSystem;
import com.hualife.aegis.mapper.UserSystemMapper;
import com.hualife.aegis.service.UserSystemService;
import com.hualife.merlin.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-18
 */
@Service
public class UserSystemServiceImpl extends ServiceImpl<UserSystemMapper, UserSystem> implements UserSystemService {
    @Autowired
    UserSystemMapper userSystemMapper;

    @Override
    public boolean deleteByUserIdAndSystemId(UserSystem userSystem) {
        Integer userId = userSystem.getUserId();
        Integer systemId = userSystem.getSystemId();
        QueryWrapper<UserSystem> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("system_id", systemId);
        UserSystem userSystemTest = getOne(wrapper);
        if (null == userSystemTest) {
            throw new BusinessException(CodeMsg.SYSTEM_AUTHORIZE_NOT_EXIST.getCode(), CodeMsg.SYSTEM_AUTHORIZE_NOT_EXIST.getMsg());
        }
        return remove(wrapper);
    }

    @Override
    public boolean addByUserIdAndSystemId(UserSystem userSystem) {
        Integer userId = userSystem.getUserId();
        Integer systemId = userSystem.getSystemId();
        QueryWrapper<UserSystem> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("system_id", systemId);
        UserSystem userSystemTest = getOne(wrapper);
        if (userSystemTest != null) {
            throw new BusinessException(CodeMsg.SYSTEM_AUTHORIZE_REPEATED.getCode(), CodeMsg.SYSTEM_AUTHORIZE_REPEATED.getMsg());
        }
        return save(userSystem);
    }
}
