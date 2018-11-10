package com.hualife.aegis.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hualife.aegis.common.CodeMsg;
import com.hualife.aegis.dto.LoginDTO;
import com.hualife.aegis.dto.UserRedisDTO;
import com.hualife.aegis.entity.User;
import com.hualife.aegis.service.UserService;
import com.hualife.merlin.common.ThreadLocalContext;
import com.hualife.merlin.exception.BusinessException;
import com.hualife.merlin.web.rest.entity.ResponseResult;
import com.hualife.merlin.web.rest.util.ResponseResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author : yangxudong
 * @Description :   登陆控制器
 * @Date : 上午11:10 2018/9/19
 */
@Api(value = "LoginController",description = "用户登陆登出接口")
@RestController
@RequestMapping("/login")
public class LoginCotroller {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * @Author : yangxudong
     * @Description : 登陆方法
     * @param user
     * @Date : 下午2:08 2018/9/19
     */
    @ApiOperation(value = "用户登陆", notes = "用户登陆接口")
    @PostMapping("/doLogin")
    public ResponseResult<String> login(@RequestBody LoginDTO user, HttpServletRequest request) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("login_name", user.getLoginName());
        User returnUser = userService.getOne(wrapper);
        if (null == returnUser) {
            throw new BusinessException(CodeMsg.USER_NOT_EXIST.getCode(),CodeMsg.USER_NOT_EXIST.getMsg());
        }
        if (!user.getPassword().equals(returnUser.getPassword())) {
            throw new BusinessException(CodeMsg.PWS_NOT_EXIST.getCode(),CodeMsg.PWS_NOT_EXIST.getMsg());
        }
        HttpSession session = request.getSession();
        UserRedisDTO userRedisDTO = new UserRedisDTO();
        BeanUtils.copyProperties(user,userRedisDTO);
        List<String> urlList = userService.findUrlListByUserId(returnUser.getId());
        userRedisDTO.setAuthorizyKey(urlList);
        //查询用户权限，保存到用户的一个属性中，一并保存至redis
        session.setAttribute("Authentication-Info",userRedisDTO);
        ThreadLocalContext.put("Authentication-Info",session.getId());
        return ResponseResultUtil.success(session.getId());
    }

    /**
     * @Author : yangxudong
     * @Description : 用户登出
     * @param request
     * @Date : 下午4:32 2018/9/19
     */
    @ApiOperation(value = "用户登出", notes = "用户退出登陆")
    @GetMapping("/logout")
    public ResponseResult<CodeMsg> logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.isNew()) {
            throw new BusinessException(CodeMsg.SESSION_INVALIDE.getCode(),CodeMsg.SESSION_INVALIDE.getMsg());
        }
        session.removeAttribute("Authentication-Info");
        return ResponseResultUtil.success();
    }

    @ApiOperation(value = "用户鉴权", notes = "用户权限鉴定")
    @PostMapping("/authorize")
    public ResponseResult<CodeMsg> authorize(@RequestBody String url,HttpServletRequest request) {
        String sessionId = request.getHeader("Authentication-Info");
        if (null == sessionId || StringUtils.isBlank(sessionId)) {
            throw new BusinessException(CodeMsg.USER_NOT_LOGIN.getCode(),CodeMsg.USER_NOT_LOGIN.getMsg());
        }
        HttpSession session = request.getSession();
        if (!sessionId.equals(session.getId())) {
            throw new BusinessException(CodeMsg.USER_NOT_LOGIN.getCode(),CodeMsg.USER_NOT_LOGIN.getMsg());
        } else if (session.isNew()) {
            throw new BusinessException(CodeMsg.SESSION_INVALIDE.getCode(),CodeMsg.SESSION_INVALIDE.getMsg());
        }
        UserRedisDTO userRedisDTO = (UserRedisDTO) session.getAttribute("Authentication-Info");
        List<String> urls = userRedisDTO.getAuthorizyKey();
        boolean flag = false;
        for (String url1 : urls) {
            if (url1.equals(url)) {
                flag = true;
            }
        }
        if (flag) {
            return ResponseResultUtil.success();
        } else {
            throw new BusinessException(CodeMsg.DONT_HAVE_AUTHORIZE.getCode(),CodeMsg.DONT_HAVE_AUTHORIZE.getMsg());
        }
    }

}
