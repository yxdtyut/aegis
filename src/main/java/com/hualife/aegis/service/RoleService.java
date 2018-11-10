package com.hualife.aegis.service;

import com.hualife.aegis.dto.OrganizationLevelDTO;
import com.hualife.aegis.dto.ResourcesLevelDTO;
import com.hualife.aegis.entity.Role;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-19
 */
public interface RoleService extends IService<Role> {
    // service层实现角色和资源的绑定保存
    void saveResourcesByrole(Integer roleId,
                             List<Integer> resourcesIds);
}
