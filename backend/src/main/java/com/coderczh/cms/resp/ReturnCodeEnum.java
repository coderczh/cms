package com.coderczh.cms.resp;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 账户端返回码枚举类
 *
 * @author Charles
 * @since 2024-09-05 13:45:15
 */
@Getter
@AllArgsConstructor
public enum ReturnCodeEnum {
    /**
     * 系统级错误
     */
    RC200(200, "操作成功"),
    RC500(500, "系统异常，请稍后再试"),

    /**
     * 全局性错误
     */
    PARAM_ERROR(9999, "参数校验错误"),

    /**
     * 业务逻辑错误
     */
    USER_INFO_ERROR(1000, "用户信息错误"),
    ROLE_INFO_ERROR(1001, "角色信息错误"),
    ;

    /**
     * 返回码
     */
    private final int code;

    /**
     * 返回信息
     */
    private final String description;
}
