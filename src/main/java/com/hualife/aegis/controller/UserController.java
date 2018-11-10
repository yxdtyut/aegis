package com.hualife.aegis.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hualife.aegis.common.CodeMsg;
import com.hualife.aegis.dto.OrganizationLevelDTO;
import com.hualife.aegis.dto.UpdateUserDTO;
import com.hualife.aegis.dto.UserAuthorizationDTO;
import com.hualife.aegis.dto.UserDTO;
import com.hualife.aegis.entity.Organization;
import com.hualife.aegis.entity.Role;
import com.hualife.aegis.entity.System;
import com.hualife.aegis.entity.User;
import com.hualife.aegis.service.*;
import com.hualife.merlin.exception.BusinessException;
import com.hualife.merlin.web.rest.entity.ResponseResult;
import com.hualife.merlin.web.rest.util.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-19
 */
@Api(value = "UserController", description = "用户操作接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private SystemService systemService;
    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private TreeService treeService;
    @Autowired
    private RoleService roleService;

    //用户查询得带上分页
    @ApiOperation(value = "用户列表", notes = "用户列表(带分页)")
    @PostMapping("/list")
    public ResponseResult<IPage<User>> userListPage(@RequestBody(required = false) UserDTO userDTO) {
       /* User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        Wrapper<User> wrapper = new QueryWrapper<>(user);
        if (null != user.getLoginName() && StringUtils.isNotBlank(user.getLoginName())) {
            ((QueryWrapper<User>) wrapper).like("login_name", user.getLoginName());
        }
        if (null != user.getName() && StringUtils.isNotBlank(user.getName())) {
            ((QueryWrapper<User>) wrapper).like("name", user.getName());
        }
        if (null != user.getOrganizationId()) {
            ((QueryWrapper<User>) wrapper).eq("organization_id", user.getOrganizationId());
        }
        ((QueryWrapper<User>) wrapper).eq("status", 1);
        Page<User> page = new Page<User>(userDTO.getCurrent(), userDTO.getSize());
        IPage<User> userPageList = userService.page(page, wrapper);*/
       UserDTO userDTO1=userDTO==null? new UserDTO():userDTO;
        Wrapper<User> wrapper = new QueryWrapper<>();
        if (null != userDTO1.getLoginName() && StringUtils.isNotBlank(userDTO1.getLoginName())) {
            ((QueryWrapper<User>) wrapper).like("login_name", userDTO1.getLoginName());
        }
        if (null != userDTO1.getName() && StringUtils.isNotBlank(userDTO1.getName())) {
            ((QueryWrapper<User>) wrapper).like("name", userDTO1.getName());
        }
        if (null != userDTO1.getOrganizationId()) {
            ((QueryWrapper<User>) wrapper).eq("organization_id", userDTO1.getOrganizationId());
        }
        ((QueryWrapper<User>) wrapper).eq("status", 1);
        Page<User> page = new Page<User>(userDTO1.getCurrent(), userDTO1.getSize());
        IPage<User> userPageList = userService.page(page, wrapper);
        return ResponseResultUtil.success(userPageList);
    }
    @ApiOperation(value = "修改用户", notes = "修改用户")
    @PostMapping("/update")
    public ResponseResult<CodeMsg> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        Wrapper<User> userWrapper = new QueryWrapper<>();
        ((QueryWrapper<User>) userWrapper).eq("id", updateUserDTO.getUserId());
        User user = userService.getOne(userWrapper);
        user.setName(updateUserDTO.getName());
        user.setOrganizationId(updateUserDTO.getOrganization_id());
        user.setUpdatedDate(new Date());
        Boolean b = userService.updateById(user);
        if (!b) {
            throw new BusinessException(CodeMsg.OPERATION_FAILED.getCode(), CodeMsg.OPERATION_FAILED.getMsg());
        }
        return ResponseResultUtil.success();
    }

    @ApiOperation(value = "删除用户", notes = "删除用户")
    @PostMapping("/delete/{id}")
    public ResponseResult<CodeMsg> deleteUser(@PathVariable Integer id) {
        User user = userService.getById(id);
        if (null == user) {
            throw new BusinessException(CodeMsg.USER_NOT_EXIST.getCode(), CodeMsg.USER_NOT_EXIST.getMsg());
        }
        user.setStatus(0);
        user.setUpdatedDate(new Date());
        userService.updateById(user);
        return ResponseResultUtil.success();
    }

    @ApiOperation(value = "用户授权之点击授权按钮展示相关系统和数据权限（点击授权按钮）", notes = "用户授权之点击授权按钮展示相关系统和数据权限（点击授权按钮）")
    @PostMapping("/authorization/{userId}")
    public ResponseResult<Map<Object, Object>> systemAndOriganizationList(@PathVariable Integer userId) {
        Map<Object, Object> map = new HashMap<>();
        List<System> systemList = systemService.getSystemByUserId(userId);
        List<OrganizationLevelDTO> organizationLevelDTOList = treeService.organizationTreeByUserId(userId);
        /*根据系统展示相关角色*/
//        List<Organization> organizationList = organizationService.getOrganizationsByUserId(userId);
        for (System system: systemList) {
            Wrapper<Role> roleQueryWrapper = new QueryWrapper<>();
            ((QueryWrapper<Role>) roleQueryWrapper).eq("system_id", system.getId());
            List<Role> roleList = roleService.list(roleQueryWrapper);
            map.put(system.getName(), roleList);
        }
        map.put("systemList", systemList);
        map.put("organizationLevelDTOList", organizationLevelDTOList);
        return ResponseResultUtil.success(map);
    }

    @ApiOperation(value = "用户授权之选中系统，角色和数据权限进行授权（点击提交按钮）", notes = "用户授权之选中系统，角色和数据权限进行授权（点击提交按钮）")
    @PostMapping("/authorization")
    public ResponseResult<CodeMsg> authorizationForUser(@RequestBody UserAuthorizationDTO userAuthorizationDTO) {
        userService.saveSystemByUserId(userAuthorizationDTO.getUserId(), userAuthorizationDTO.getSystemIds());
        userService.saveRoleByUserId(userAuthorizationDTO.getUserId(), userAuthorizationDTO.getRoleIds());
        userService.saveOriganizationByUserId(userAuthorizationDTO.getUserId(), userAuthorizationDTO.getOrganizationIds());
        return ResponseResultUtil.success();
    }

}

