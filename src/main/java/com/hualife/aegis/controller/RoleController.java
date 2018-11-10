package com.hualife.aegis.controller;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hualife.aegis.common.CodeMsg;
import com.hualife.aegis.dto.OrganizationLevelDTO;
import com.hualife.aegis.dto.ResourcesLevelDTO;
import com.hualife.aegis.dto.SaveResourcesAndRoleDTO;
import com.hualife.aegis.dto.UpdateRoleDTO;
import com.hualife.aegis.entity.Resources;
import com.hualife.aegis.entity.Role;
import com.hualife.aegis.service.ResourcesService;
import com.hualife.aegis.service.RoleService;
import com.hualife.aegis.service.TreeService;
import com.hualife.merlin.exception.BusinessException;
import com.hualife.merlin.web.rest.entity.ResponseResult;
import com.hualife.merlin.web.rest.util.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-19
 */
@Api(value = "RoleController", description = "角色操作接口")
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private TreeService treeService;
    @Autowired
    private ResourcesService resourcesService;
    /*角色页面开始展示系统信息，调用system系统模块的systemList接口即可*/


    @ApiOperation(value = "角色查询", notes = "查询某个系统下的所有角色")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "String", dataType = "String", name = "id", required = true)
    })
    @PostMapping("/list/{systemId}")
    public ResponseResult<List<Role>> roleListBySystemId(@PathVariable String systemId) {
        Wrapper<Role> roleWrapper = new QueryWrapper<>();
        ((QueryWrapper<Role>) roleWrapper).eq("status", 1);
        ((QueryWrapper<Role>) roleWrapper).eq("system_id", systemId);
        List<Role> roleList = roleService.list(roleWrapper);
        return ResponseResultUtil.success(roleList);
    }

//    @ApiOperation(value = "增加角色", notes = "增加角色")
//    @PostMapping("/add")
//    public ResponseResult<CodeMsg> addRole(@RequestBody Role role) {
//        return ResponseResultUtil.success(CodeMsg.SUCCESS);
//    }

    @ApiOperation(value = "修改角色", notes = "修改角色")
    @PostMapping("/update")
    public ResponseResult<CodeMsg> updateRole(@RequestBody UpdateRoleDTO updateRoleDTO) {
        Wrapper<Role> roleWrapper = new QueryWrapper<>();
        ((QueryWrapper<Role>) roleWrapper).eq("id", updateRoleDTO.getRoleId());
        Role role = roleService.getOne(roleWrapper);
        role.setName(updateRoleDTO.getNewRoleName());
        role.setUpdatedDate(new Date());
        Boolean updateById = roleService.updateById(role);
        if (!updateById) {
            throw new BusinessException(CodeMsg.OPERATION_FAILED.getCode(), CodeMsg.OPERATION_FAILED.getMsg());
        } else {
            return ResponseResultUtil.success();
        }

    }
    @ApiOperation(value = "删除角色", notes = "删除角色")
    @PostMapping("/delete/{id}")
    public ResponseResult<CodeMsg> deleteRole(@PathVariable Integer id) {
        Role role = roleService.getById(id);
        if (null == role) {
            throw new BusinessException(CodeMsg.ROLE_NOT_EXIST.getCode(), CodeMsg.ROLE_NOT_EXIST.getMsg());
        }
        role.setStatus(0);
        role.setUpdatedDate(new Date());
        roleService.updateById(role);
        return ResponseResultUtil.success();
    }

    @ApiOperation(value = "角色资源权限", notes = "角色资源权限")
    @PostMapping("/resources/{roleId}")
//    ResponseResult<List<ResourcesLevelDTO>>
    public ResponseResult<List<ResourcesLevelDTO>> roleResources(@PathVariable Integer roleId) {
        List<ResourcesLevelDTO> resourcesLevelDTOList = treeService.resourcesTreeByRoleId(roleId);
//       List<Resources> resourcesList=resourcesService.getResourcesByRoleId(id);
        return ResponseResultUtil.success(resourcesLevelDTOList);
    }

    //    角色和机构没有直接关系
    @ApiOperation(value = "角色机构", notes = "角色机构")
    @PostMapping("/organization/{id}")
    public ResponseResult<List<OrganizationLevelDTO>> roleOrganization(@PathVariable Integer id) {
        List<OrganizationLevelDTO> organizationLevelDTOList = new ArrayList<>();
        //        organizationLevelDTOList=roleService.findOrganizationByRoleId(id);
        return ResponseResultUtil.success(organizationLevelDTOList);
    }

    @ApiOperation(value = "角色资源保存", notes = "角色资源保存")
    @PostMapping("/saveResources")
    public ResponseResult<CodeMsg> saveRoleResources(@RequestBody SaveResourcesAndRoleDTO saveResourcesAndRoleDTO) {

        roleService.saveResourcesByrole(saveResourcesAndRoleDTO.getRoleId(), saveResourcesAndRoleDTO.getResourcesIds());
        return ResponseResultUtil.success();
    }

    //角色和机构没有直接关系
    @ApiOperation(value = "角色对应机构保存", notes = "角色对应机构保存")
    @PostMapping("/saveOrganization")
    public ResponseResult<CodeMsg> saveOrganization(@RequestParam Integer roleId,
                                                    @RequestParam List<String> organizationIds) {
        return ResponseResultUtil.success();
    }

}

