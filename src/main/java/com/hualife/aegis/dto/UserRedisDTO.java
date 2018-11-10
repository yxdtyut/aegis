package com.hualife.aegis.dto;

import com.hualife.aegis.entity.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Author : yangxudong
 * @Description :   用户信息保存到redis当中所使用的对象
 * @Date : 下午2:03 2018/9/21
 */
@Data
public class UserRedisDTO extends LoginDTO implements Serializable {
    private List<String> authorizyKey;
}
