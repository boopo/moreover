package com.lv.spring;

import com.lv.spring.enums.PermissionEnum;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public class normal {
    public static void main(String[] args) {
//
        findList();
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

    public static void findList(){
        List<String> list1 = new ArrayList<>();
        list1.add("08183109");
        list1.add("08182222");
        System.out.println(list1);
        Optional<String> f = list1.stream().filter(p -> list1.contains("08183109")).findFirst();
        System.out.println(f.isPresent());
        System.out.println(f.get());
    }
}
