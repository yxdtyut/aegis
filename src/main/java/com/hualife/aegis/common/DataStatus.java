package com.hualife.aegis.common;


/**
 * @Author : yangxudong
 * @Description : 状态枚举类
 * @Date : 下午4:36 2018/9/25
 */
public enum DataStatus {

    NORMAL(1),UNUSE(0);

    private Integer value;

    DataStatus(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
