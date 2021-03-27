package com.lv.spring.service.impl;

import com.lv.spring.context.UserContext;
import com.lv.spring.entity.CommentPost;
import com.lv.spring.entity.Post;
import com.lv.spring.repository.CommentRepository;
import com.lv.spring.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
        commentPost.setPostId(postId);
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING)
                .withIgnoreCase(true);
        Example <CommentPost> example = Example.of(commentPost, matcher);
        Page<CommentPost> commentPosts = commentRepository.findAll(example, pageable);
        return commentPosts;
    }

    @Override
    public boolean star(String s) {
        return false;
    }

    @Override
    public boolean unstar(String s) {
        return false;
    }

    @Override
    public List getStarList(String commentId) {
        return null;
    }

    @Override
    public CommentPost commit(String message, String postId) {
        List<String>list = null;
        CommentPost commentPost = new CommentPost();
        commentPost.setCreateTime(new Date());
        commentPost.setUpdateTime(new Date());
        commentPost.setPostId(postId);
        commentPost.setParentId("0");
        commentPost.setStar(0);
        commentPost.setStarList(list);
        commentPost.setMessage(message);
        commentPost.setIsDeleted(0);
        commentPost.setPublisher(UserContext.getCurrentUserName());
        commentRepository.save(commentPost);
        return commentPost;
    }

    @Override
    public CommentPost commit(String message, String postId, String parentId) {
        List<String>list = null;
        CommentPost commentPost = new CommentPost();
        commentPost.setCreateTime(new Date());
        commentPost.setUpdateTime(new Date());
        commentPost.setPostId(postId);
        commentPost.setParentId(parentId);
        commentPost.setStar(0);
        commentPost.setStarList(list);
        commentPost.setMessage(message);
        commentPost.setIsDeleted(0);
        commentPost.setPublisher(UserContext.getCurrentUserName());
        commentRepository.save(commentPost);
        return commentPost;
    }
}
