package com.lv.spring;

import com.lv.spring.entity.User;
import com.lv.spring.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class test {
    @Autowired
    public UserMapper userMapper;

    @Test
    public void insert() {
        User user = new User();
        user.setUsername("noir");
        user.setPassword("123456");
        int count = userMapper.updateById(user);
        System.out.println(count);

    }

    @Test
    public void validUserTest() {
        User user = new User();
        String username = new String("");
        String password = new String("");
        user.setUsername(username);
        user.setPassword(password);
  //      int count = usersMapper.
    }

//    //mq复杂操作
//    @Test
//    public void testSelect() {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        //ge gt le lt
//        //queryWrapper.ge("age",20);
//
//        //eq, ne
//        //queryWrapper.eq("name","tom");
//
//        //between
//        //queryWrapper.between("age",24,28);
//
//        //like notlike likeleft likeright
//        //queryWrapper.like("name","n");
//
//        //orderBy orderByDesc orderByAsc
//        queryWrapper.orderByDesc("id");
//
//
//        List<User> users = userMapper.selectList(queryWrapper);
//        System.out.println(users);
//
//    }
//
//    //根据Id删除
//    @Test
//    public void testDeleteId(){
//        int rows = userMapper.deleteById(1370269352396996610L);
//        System.out.println(rows);
//    }
//
//    //分页查询
//    @Test
//    public void testSelectPage() {
//        Page<User> page = new Page(1,3);
//        Page<User> userPage = userMapper.selectPage(page, null);
//        //返回对象得到分页所有数据
//        long pages = userPage.getPages(); //总页数
//        long current = userPage.getCurrent(); //当前页
//        List<User> records = userPage.getRecords(); //查询数据集合
//        long total = userPage.getTotal(); //总记录数
//        boolean hasNext = userPage.hasNext();  //下一页
//        boolean hasPrevious = userPage.hasPrevious(); //上一页
//
//        System.out.println(pages);
//        System.out.println(current);
//        System.out.println(records);
//        System.out.println(total);
//        System.out.println(hasNext);
//        System.out.println(hasPrevious);
//
//    }
//
//    //按条件查询
//    @Test
//    public void testSelect2(){
//        Map<String, Object> columnMap = new HashMap<>();
//        columnMap.put("name","jack");
//        columnMap.put("age",20);
//        List<User> users = userMapper.selectByMap(columnMap);
//        System.out.println(users);
//    }
//    //id 多查询
//    @Test
//    public void testSelect1(){
//        List<User> users = userMapper.selectBatchIds(Arrays.asList(1,2,3));
//        System.out.println(users);
//    }
//
//    @Test
//    public void testOptimisticLocker(){
//        //根据ID查询
//        User user = userMapper.selectById(1370270555549863938L);
//        //修改
//        user.setName("jucfc");
//        userMapper.updateById(user);
//    }
//
//    @Test
//    public void  testUpdate(){
//        User user = new User();
//        user.setId(9L);
//        user.setName("lucymary00000y");
//        int count = userMapper.updateById(user);
//        System.out.println(count);
//    }
//
//    @Test
//    public void testAdd() {
//        User user = new User();
//        user.setName("lvvv");
//        user.setAge(20);
//        user.setEmail("1662870160@qq.com");
//        int insert = userMapper.insert(user);
//        System.out.println(insert);
//    }
//
//    @Test
//    public void findAll() {
//        List<User> users = userMapper.selectList(null);
//        System.out.println(users);
//    }
}
