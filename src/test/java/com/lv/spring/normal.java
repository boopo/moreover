package com.lv.spring;

import com.lv.spring.enums.PermissionEnum;

import java.text.SimpleDateFormat;
import java.util.Date;

public class normal {
    public static void main(String[] args) {
        System.out.println(check(2,2));
        System.out.println(check(2,1));
        System.out.println(check(2,32));
        System.out.println(check(2,1));
        System.out.println(check(4,2));
        System.out.println(check(8,32));
        System.out.println(check(8,32));
    }

    public static void heelo(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj = sdf.format(d);
        System.out.println(d);
        System.out.println(sj);
    }
    public static void h1(){
        Integer a = 2;
        Integer b =2;

        System.out.println(a ==b);
        System.out.println(a.equals(b));
        System.out.println(a == PermissionEnum.NORMAL.getAuthority());
        System.out.println(a.equals(PermissionEnum.NORMAL.getAuthority()) );
    }

    public static boolean check(Integer code, Integer self){
        if (self == (PermissionEnum.ROOT.getAuthority())) return true;
        if ((PermissionEnum.PROHIBIT.getAuthority() & self) == PermissionEnum.PROHIBIT.getAuthority()) return false;
        return (code & self) == code;
    }
}
