package com.lv.spring.enums;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

@Getter
public enum ResultVOEnum {
    SUCCESS(200,"成功"),
    LOGIN_REQUIRED(203,"请登录"),
    FAIL(400, "请求失败"),
    AUTH_FAIL(401,"认证失败"),
    USERNAME_WRONG(1000,"用户名错误"),
    PASSWORD_WRONG(1001,"密码错误"),
    REGISTERED(1002,"用户已注册"),
    PARAMETER_ERROR(1003,"请检查你的参数是否正确"),
    NOT_FOUND(1004,"资源不存在"),
    REPEAT_FORBIDDEN(1005,"禁止重复操作"),
    USER_NOT_EXISTS(1006,"该用户不在列表中")
    ;



    private Integer code;
    private String message;

    private ResultVOEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
