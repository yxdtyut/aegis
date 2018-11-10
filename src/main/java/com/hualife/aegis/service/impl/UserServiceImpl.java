package com.hualife.aegis.service.impl;

import com.hualife.aegis.entity.*;
import com.hualife.aegis.mapper.UserMapper;
import com.hualife.aegis.mapper.UserOrganizationMapper;
import com.hualife.aegis.mapper.UserRoleMapper;
import com.hualife.aegis.mapper.UserSystemMapper;
import com.hualife.aegis.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.bytebuddy.asm.Advice;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserSystemMapper userSystemMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserOrganizationMapper userOrganizationMapper;


    @Override
    public List<String> findUrlListByUserId(Integer id) {
        return userMapper.findUrlListByUserId(id);
    }

    //    逻辑删除用户,通过将user表中的status的值从1改为0来实现
    public void deleteUser(Integer userId) {
        User user = userMapper.selectById(userId);

        userMapper.updateById(user);
    }

    /*用户授权根据用户id保存系统*/
    public void saveSystemByUserId(Integer userId, List<Integer> systemIds) {
        for (Integer systemId : systemIds) {
            UserSystem userSystem = new UserSystem();
            userSystem.setUserId(userId);
            userSystem.setSystemId(systemId);
            userSystemMapper.insert(userSystem);
        }
    }

    ;

    /*用户授权根据用户id保存角色*/
    public void saveRoleByUserId(Integer userId, List<Integer> roleIds) {
        for (Integer roleId : roleIds) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleId);
            userRoleMapper.insert(userRole);
        }
    }

    ;

    /*用户授权根据用户id保存数据权限*/
    public void saveOriganizationByUserId(Integer userId, List<Integer> origanizationIds) {
        for (Integer origanizationId : origanizationIds) {
            UserOrganization userOrganization = new UserOrganization();
            userOrganization.setUserId(userId);
            userOrganization.setOrganizationId(origanizationId);
            userOrganizationMapper.insert(userOrganization);
        }
    }

    ;
}
