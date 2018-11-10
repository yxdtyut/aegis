package com.hualife.aegis.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hualife.aegis.entity.UserSystem;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-18
 */
public interface UserSystemService extends IService<UserSystem> {

    boolean deleteByUserIdAndSystemId(UserSystem userSystem);

    boolean addByUserIdAndSystemId(UserSystem userSystem);
}
