package com.lv.spring.service.impl;

import com.lv.spring.context.UserContext;
import com.lv.spring.entity.CommentPost;
import com.lv.spring.entity.Post;
import com.lv.spring.enums.ResultVOEnum;
import com.lv.spring.exceptioin.ApiException;
import com.lv.spring.repository.CommentRepository;
import com.lv.spring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentRepository commentRepository;


    @Override
    public Page<CommentPost> getCommentParentsByPage(String postId, Integer page, Integer limit) {
        Sort sort = Sort.by(Sort.Direction.DESC,"star","updateTime");
        Pageable pageable = PageRequest.of(page - 1 , limit, sort);
        CommentPost commentPost = new CommentPost();
        commentPost.setIsDeleted(0);
        commentPost.setParentId("0");
        commentPost.setIsChildren(0);
        commentPost.setPostId(postId);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        Example <CommentPost> example = Example.of(commentPost, matcher);
        Page<CommentPost> commentPosts = commentRepository.findAll(example, pageable);
        return commentPosts;
    }

    @Override
    public Page<CommentPost> getCommentChildren(String postId, String parentId) {
        Sort sort = Sort.by(Sort.Direction.DESC,"star","updateTime");
        Pageable pageable = PageRequest.of(0, 100, sort);
        CommentPost commentPost = new CommentPost();
        commentPost.setIsDeleted(0);
        commentPost.setParentId(parentId);
        commentPost.setIsChildren(1);
        commentPost.setPostId(postId);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        Example <CommentPost> example = Example.of(commentPost, matcher);
        Page<CommentPost> commentPosts = commentRepository.findAll(example, pageable);
        return commentPosts;
    }

    @Override
    public boolean star(String id) {
        CommentPost postInfo = getCommentPost(id);
        List<String> list = postInfo.getStarList();
        Optional<String> f = list.stream().filter(p -> list.contains(UserContext.getCurrentUserName())).findFirst();
        if(f.isPresent()){
            throw new ApiException(ResultVOEnum.REPEAT_FORBIDDEN);
        }
        list.add(UserContext.getCurrentUserName());
        Integer star = list.size();
        postInfo.setStar(star);
        postInfo.setStarList(list);
        commentRepository.save(postInfo);
        return true;
    }

    @Override
    public boolean unstar(String id) {
        CommentPost postInfo = getCommentPost(id);
        List<String> list = postInfo.getStarList();
        Optional<String> f = list.stream().filter(p -> list.contains(UserContext.getCurrentUserName())).findFirst();
        if(!f.isPresent()){
            throw new ApiException(ResultVOEnum.REPEAT_FORBIDDEN);
        }
        Integer star = list.size() -1;
        list.remove(UserContext.getCurrentUserName());
        postInfo.setStarList(list);
        postInfo.setStar(star);
        commentRepository.save(postInfo);
        return true;
    }

    @Override
    public List getStarList(String commentId) {
        return null;
    }

    @Override
    public CommentPost commit(String message, String postId) {
        List<String>list = new ArrayList<>();
        CommentPost commentPost = new CommentPost();
        commentPost.setCreateTime(new Date());
        commentPost.setUpdateTime(new Date());
        commentPost.setPostId(postId);
        commentPost.setParentId("0");
        commentPost.setStar(0);
        commentPost.setIsChildren(0);
        commentPost.setStarList(list);
        commentPost.setMessage(message);
        commentPost.setIsDeleted(0);
        commentPost.setPublisher(UserContext.getCurrentUserName());
        commentRepository.save(commentPost);
        return commentPost;
    }

    @Override
    public CommentPost commit(String message, String postId, String parentId) {
        List<String>list = new ArrayList<>();
        CommentPost commentPost = new CommentPost();
        commentPost.setCreateTime(new Date());
        commentPost.setUpdateTime(new Date());
        commentPost.setPostId(postId);
        commentPost.setParentId(parentId);
        commentPost.setIsChildren(1);
        commentPost.setStar(0);
        commentPost.setStarList(list);
        commentPost.setMessage(message);
        commentPost.setIsDeleted(0);
        commentPost.setPublisher(UserContext.getCurrentUserName());
        commentRepository.save(commentPost);
        return commentPost;
    }

    @Override
    public CommentPost getCommentPost(String id) {
        return commentRepository.findById(id).get();
    }
}
