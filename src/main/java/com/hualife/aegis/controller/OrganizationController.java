package com.hualife.aegis.controller;


import com.hualife.aegis.common.CodeMsg;
import com.hualife.aegis.dto.OrganizationDTO;
import com.hualife.aegis.dto.OrganizationLevelDTO;
import com.hualife.aegis.entity.Organization;
import com.hualife.aegis.service.OrganizationService;
import com.hualife.aegis.service.TreeService;
import com.hualife.merlin.exception.BusinessException;
import com.hualife.merlin.web.rest.entity.ResponseResult;
import com.hualife.merlin.web.rest.util.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-20
 */
@Api(value = "OrganizationController", description = "机构操作接口")
@RestController
@RequestMapping("/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private TreeService treeService;

    @ApiOperation(value = "机构列表", notes = "机构列表接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "String", dataType = "String", name = "name", required = false)
    })
    @PostMapping("/list")
    public ResponseResult<List<OrganizationLevelDTO>> organizationList(@RequestBody(required = false) OrganizationDTO organizationDTO) {
        List<OrganizationLevelDTO> organizationLevelDTOList = treeService.organizationTree(organizationDTO);
        return ResponseResultUtil.success(organizationLevelDTOList);
    }

    @ApiOperation(value = "机构查询", notes = "单个机构查询")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "String", dataType = "String", name = "id", required = true)
    })
    @PostMapping("/search/{id}")
    public ResponseResult<OrganizationDTO> organizationSearch(@PathVariable String id) {
        Organization organization = organizationService.getById(id);
        if (null == organization) {
            throw new BusinessException(CodeMsg.ORGANIZATION_NOT_EXIST.getCode(), CodeMsg.ORGANIZATION_NOT_EXIST.getMsg());
        }
        OrganizationDTO organizationDTO = new OrganizationDTO();
        BeanUtils.copyProperties(organization, organizationDTO);
        return ResponseResultUtil.success(organizationDTO);
    }
}

