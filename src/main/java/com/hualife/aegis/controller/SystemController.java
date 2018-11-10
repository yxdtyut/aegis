package com.hualife.aegis.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hualife.aegis.common.CodeMsg;
import com.hualife.aegis.common.DataStatus;
import com.hualife.aegis.dto.SystemDTO;
import com.hualife.aegis.dto.UserSystemDTO;
import com.hualife.aegis.entity.System;
import com.hualife.aegis.entity.User;
import com.hualife.aegis.entity.UserSystem;
import com.hualife.aegis.service.SystemService;
import com.hualife.merlin.exception.BusinessException;
import com.hualife.merlin.web.rest.entity.ResponseResult;
import com.hualife.merlin.web.rest.util.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-20
 */
@Api(value = "SystemController", description = "系统操作接口")
@RestController
@RequestMapping("/system")
public class SystemController {

    @Autowired
    private SystemService systemService;

    @ApiOperation(value = "系统列表", notes = "系统列表接口")
    @PostMapping("/list")
    public ResponseResult<IPage<System>> systemList(@RequestBody(required = false) SystemDTO systemDTO) {
        SystemDTO systemDTO1 = systemDTO == null ? new SystemDTO() : systemDTO;
        Page<System> page = new Page<>(systemDTO1.getCurrent(), systemDTO1.getSize());
        return ResponseResultUtil.success(systemService.getSystemsPage(page, systemDTO1));
    }

    @ApiOperation(value = "查看系统秘钥", notes = "查看系统秘钥")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "String", dataType = "String", name = "id", required = true)
    })
    @PostMapping("/keys/{id}")
    public ResponseResult<String> systemKeys(@PathVariable String id) {
        String stegoKey = systemService.getStegoKeyById(id);
        return ResponseResultUtil.success(stegoKey);
    }

    @ApiOperation(value = "增加系统", notes = "增加系统")
    @PostMapping("/add")
    public ResponseResult<CodeMsg> addSystem(@RequestBody System system) {
        system.setCreatedDate(new Date());
        boolean insert = systemService.save(system);
        if (insert) {
            return ResponseResultUtil.success();
        } else {
            throw new BusinessException(CodeMsg.OPERATION_FAILED.getCode(), CodeMsg.OPERATION_FAILED.getMsg());
        }
    }

    @ApiOperation(value = "修改系统", notes = "修改系统")
    @PostMapping("/update")
    public ResponseResult<CodeMsg> updateSystem(@RequestBody System system) {
        system.setUpdatedDate(new Date());
        boolean updateById = systemService.updateById(system);
        if (updateById) {
            return ResponseResultUtil.success();
        } else {
            throw new BusinessException(CodeMsg.OPERATION_FAILED.getCode(), CodeMsg.OPERATION_FAILED.getMsg());
        }
    }

    @ApiOperation(value = "删除系统", notes = "删除系统")
    @PostMapping("/delete/{id}")
    public ResponseResult<CodeMsg> updateSystem(@PathVariable String id) {
        System system = systemService.getById(id);
        if (null == system) {
            throw new BusinessException(CodeMsg.SYSTEM_NOT_EXIST.getCode(), CodeMsg.SYSTEM_NOT_EXIST.getMsg());
        }
        system.setUpdatedDate(new Date());
        system.setStatus(DataStatus.UNUSE.getValue());
        systemService.updateById(system);
        return ResponseResultUtil.success();
    }

    @ApiOperation(value = "用户授权", notes = "为用户授权")
    @PostMapping("/authorization/add")
    public ResponseResult<CodeMsg> addAuthorization(@RequestBody UserSystem userSystem) {
        userSystem.setCreatedDate(new Date());
        boolean addAuthorization = systemService.addAuthorization(userSystem);
        if (addAuthorization) {
            return ResponseResultUtil.success();
        } else {
            throw new BusinessException(CodeMsg.OPERATION_FAILED.getCode(), CodeMsg.OPERATION_FAILED.getMsg());
        }
    }

    @ApiOperation(value = "用户撤权", notes = "为用户撤权")
    @PostMapping("/authorization/delete")
    public ResponseResult<CodeMsg> deleteAuthorization(@RequestBody UserSystem userSystem) {
        boolean deleteAuthorization = systemService.deleteAuthorization(userSystem);
        if (deleteAuthorization) {
            return ResponseResultUtil.success();
        } else {
            throw new BusinessException(CodeMsg.OPERATION_FAILED.getCode(), CodeMsg.OPERATION_FAILED.getMsg());
        }

    }

    @ApiOperation(value = "系统的授权详情", notes = "系统的授权详情")
    @PostMapping("/authorization/list")
    public ResponseResult<Map> listAuthorization(@RequestBody(required = false) UserSystemDTO userSystemDTO) {
        UserSystemDTO userSystemDTO1 = userSystemDTO == null ? new UserSystemDTO() : userSystemDTO;
        Page<User> aPage = new Page<>(userSystemDTO1.getACurrent(), userSystemDTO1.getASize());
        Page<User> bPage = new Page<>(userSystemDTO1.getBCurrent(), userSystemDTO1.getBSize());
        Map<String, IPage> map = new LinkedHashMap<>();
        map.put("aPage", systemService.getAuthorizedUsersPage(aPage, userSystemDTO1));
        map.put("bPage", systemService.getNotAuthorizedUsersPage(bPage, userSystemDTO1));
        return ResponseResultUtil.success(map);
    }
}

