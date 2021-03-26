package com.lv.spring.enums;

import lombok.Getter;

@Getter
public enum PermissionEnum {

    PROHIBIT(1, "黑名单用户"),
    NORMAL(2, "普通用户"),
    ORGANIZER(4, "发布者"),
    VIP(8,"会员"),
    ADMIN(16, "管理员"),
    ROOT(32,"root");


    private Integer authority;
    private String message;

    private PermissionEnum(Integer authority, String message) {
        this.authority = authority;
        this.message = message;
    }

}
