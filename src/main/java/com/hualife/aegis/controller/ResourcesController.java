package com.hualife.aegis.controller;


import com.hualife.aegis.common.CodeMsg;
import com.hualife.aegis.common.DataStatus;
import com.hualife.aegis.dto.ResourcesDTO;
import com.hualife.aegis.dto.ResourcesLevelDTO;
import com.hualife.aegis.entity.Resources;
import com.hualife.aegis.service.ResourcesService;
import com.hualife.aegis.service.TreeService;
import com.hualife.aegis.util.LevelUtil;
import com.hualife.merlin.exception.BusinessException;
import com.hualife.merlin.web.rest.entity.ResponseResult;
import com.hualife.merlin.web.rest.util.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author yangxudong
 * @since 2018-09-19
 */
@Api(value = "ResourcesController",description = "资源相关接口")
@RestController
@RequestMapping("/resources")
public class ResourcesController {
    @Autowired
    private ResourcesService resourcesService;

    @Autowired
    private TreeService treeService;

    @ApiOperation(value = "添加资源", notes = "添加资源接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body",dataType = "Resources",name = "resources",required = true)
    })
    @PostMapping("/add")
    public ResponseResult<CodeMsg> addResources(@RequestBody Resources resources) {
        resources.setLevel(LevelUtil.calculateLevel(getParentLevel(resources.getParentId()),resources.getParentId()));
        resources.setCreatedDate(new Date());
        boolean insert = resourcesService.save(resources);
        if (insert) {
            return ResponseResultUtil.success();
        } else {
            throw new BusinessException(CodeMsg.OPERATION_FAILED.getCode(), CodeMsg.OPERATION_FAILED.getMsg());
        }
    }

    @ApiOperation(value = "修改资源", notes = "修改资源接口")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body",dataType = "Resources",name = "resources",required = true)
    })
    @PostMapping("/update")
    public ResponseResult<CodeMsg> updateResources(@RequestBody Resources resources) {
        resources.setLevel(LevelUtil.calculateLevel(getParentLevel(resources.getParentId()),resources.getParentId()));
        resources.setUpdatedDate(new Date());
        boolean updateById = resourcesService.updateById(resources);
        if (updateById) {
            return ResponseResultUtil.success();
        } else {
            throw new BusinessException(CodeMsg.OPERATION_FAILED.getCode(), CodeMsg.OPERATION_FAILED.getMsg());
        }
    }

    @ApiOperation(value = "删除资源", notes = "删除资源接口")
    @PostMapping("/delete/{id}")
    public ResponseResult<CodeMsg> deleteResources(@PathVariable Integer id) {
        Resources resources = resourcesService.getById(id);
        resources.setUpdatedDate(new Date());
        if (null == resources) {
            throw new BusinessException(CodeMsg.RESOURCES_NOT_EXIST.getCode(),CodeMsg.RESOURCES_NOT_EXIST.getMsg());
        }
        resources.setStatus(DataStatus.UNUSE.getValue());
        resourcesService.updateById(resources);
        return ResponseResultUtil.success();
    }

    @ApiOperation(value = "资源列表", notes = "资源列表接口，可以按条件查询")
    @PostMapping("/list")
    public ResponseResult<List<ResourcesLevelDTO>> resourcesList(@RequestBody ResourcesDTO resourcesDTO) {
        List<ResourcesLevelDTO> resourcesLevelDTOList = treeService.resourcesTree(resourcesDTO);
        return ResponseResultUtil.success(resourcesLevelDTOList);
    }

    /**
     * @Author : yangxudong
     * @Description :   获取上级资源级别
     * @param parentId
     * @Date : 下午6:29 2018/9/19
     */
    private String getParentLevel(Integer parentId) {
        Resources parentResources = resourcesService.getById(parentId);
        if (null == parentResources) {
            return null;
        }
        return parentResources.getLevel();
    }
}

