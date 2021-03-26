package com.lv.spring.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lv.spring.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
}
