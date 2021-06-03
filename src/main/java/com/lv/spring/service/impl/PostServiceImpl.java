package com.lv.spring.service.impl;
import com.lv.spring.context.UserContext;
import com.lv.spring.entity.Post;
import com.lv.spring.entity.UserInfo;
import com.lv.spring.enums.ResultVOEnum;
import com.lv.spring.exceptioin.ApiException;
import com.lv.spring.repository.PostRepository;
import com.lv.spring.repository.UserInfoRepository;
import com.lv.spring.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserInfoRepository userInfoRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public void save(Post post) throws ParseException {
        Date date = new Date();
        post.setStar(0);
        post.setCreateTime(date);
        post.setUpdateTime(date);
        post.setIsDeleted(0);
        post.setStar(0);
        post.setPublisher(UserContext.getCurrentUserName());
        List<String> list = new ArrayList();
        post.setStarList(list);
        post.setCollection(0);
        post.setHead("https://moreover.atcumt.com/userinfo/head/"+UserContext.getCurrentUserName());
        postRepository.save(post);
    }

    @Override
    public void update(String id, Post post) {
        Post oldPost = postRepository.findById(id).get();
        if (oldPost.getIsDeleted().equals(1)) throw new ApiException(ResultVOEnum.NOT_FOUND);
        oldPost.setTitle(post.getTitle());
        oldPost.setStartTime(post.getStartTime());
        oldPost.setCutoffTime(post.getCutoffTime());
        oldPost.setLocation(post.getLocation());
        oldPost.setOutline(post.getOutline());
        oldPost.setDetail(post.getDetail());
        oldPost.setOriginalImage(post.getOriginalImage());
        oldPost.setThumbnailImage(post.getThumbnailImage());

        oldPost.setUpdateTime(new Date());

        postRepository.save(oldPost);
    }

    @Override
    public void deleted(String id) {
        Post post = postRepository.findById(id).get();
        post.setIsDeleted(1);
        postRepository.save(post);
    }

    @Override
    public List<Post> findAll() {
        List<Post> list;
        list = postRepository.findAll();
        return list;
    }

    @Override
    public Page<Post> findByPage(Integer page, Integer limit) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(page - 1 , limit, sort);
        Post post = new Post();
        post.setIsDeleted(0);


        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);

        Example <Post> example = Example.of(post ,matcher);
        Page<Post> posts = postRepository.findAll(example,pageable);
        return posts;
    }
    @Override
    public Post getOnePost(String id){
        return postRepository.findById(id).get();
    }

    @Override
    public void starPost(String id) {
        String username = UserContext.getCurrentUserName();
        Post post = getOnePost(id);
        List<String> list = post.getStarList();
        Optional<String> f = list.stream().filter(p -> list.contains(username)).findFirst();
        if(f.isPresent()){
            throw new ApiException(ResultVOEnum.REPEAT_FORBIDDEN);
        }
        list.add(username);
        post.setStarList(list);
        post.setStar(list.size());
        postRepository.save(post);
    }

    @Override
    public void collectPost(String id) {
        String username = UserContext.getCurrentUserName();
        Query query = new Query(Criteria.where("username").is(username));
        UserInfo userInfo = mongoTemplate.findOne(query, UserInfo.class);

        List<String> list = userInfo.getCollection();
        Optional<String> f = list.stream().filter(p -> list.contains(id)).findFirst();
        if(f.isPresent()){
            throw new ApiException(ResultVOEnum.REPEAT_FORBIDDEN);
        }
        list.add(id);
        Post post = getOnePost(id);
        post.setCollection(post.getCollection()+1);
        postRepository.save(post);
        userInfoRepository.save(userInfo);
    }
}
