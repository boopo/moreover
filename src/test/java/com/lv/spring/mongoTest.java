package com.lv.spring;

import com.lv.spring.entity.Post;
import com.lv.spring.entity.UserInfo;
import com.lv.spring.repository.PostRepository;
import com.lv.spring.repository.UserInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class mongoTest {
    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void save() {
        List<String> list = new ArrayList();
        list.add("url1");
        list.add("url2");
        list.add("url3");

        Post post = new Post();
        post.setPublisher("hello");
        post.setTitle("你好");
        post.setStartTime(String.valueOf(System.currentTimeMillis()));
        post.setCutoffTime(String.valueOf(System.currentTimeMillis()));
        post.setLocation("cumt");
        post.setDetail("some detail");
        post.setOutline("outline");
        post.setStar(1);
        post.setThumbnailImage(list);
        post.setOriginalImage(list);

        Post p1 = postRepository.save(post);
        System.out.println(p1);
    }
    @Test
    public void find() {
        List<Post> list = new ArrayList();
        list = postRepository.findAll();
        System.out.println(list);

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sj = sdf.format(d);
        System.out.println(sj);
    }

    @Test
    public void getByPage() {

    }

    @Test
    public void getoneUser(){
        Query query = new Query(Criteria.where("username").is("1234567"));
        UserInfo u = mongoTemplate.findOne(query, UserInfo.class);
        System.out.println(u);
    }

}
