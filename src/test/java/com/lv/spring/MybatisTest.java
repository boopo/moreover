package com.lv.spring;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lv.spring.entity.User;
import com.lv.spring.mapper.UserMapper;
import com.lv.spring.utils.MD5;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class MybatisTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("lv");
        user.setPassword("123456");
        int count = userMapper.insert(user);
        System.out.println(count);
    }

    @Test
    public void findAll() {
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }

    @Test
    public void testDelete(){
        int rows = userMapper.deleteById(1);
        System.out.println(rows);
    }
    @Test
    public void getvoid() {
        User user = userMapper.selectById(9999L);
        System.out.println(user);
    }
    @Test
    public void getusername() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username","lov").last("limit 1");
        Integer users = userMapper.selectCount(queryWrapper);
        System.out.println(users.intValue());
    }
    @Test
    public void md5(){
        String a = "123456";
        System.out.println(MD5.encrypt(a));
    }
}
