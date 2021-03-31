package com.lv.spring.service;

import com.lv.spring.entity.CommentPost;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CommentService {
    Page<CommentPost> getCommentParentsByPage(String postId, Integer page, Integer limit);
    Page<CommentPost> getCommentChildren(String postId, String parentId);
    boolean star(String username);
    boolean unstar(String username);
    List getStarList(String commentId);
    CommentPost commit(String message, String postId);
    CommentPost commit(String message, String postId, String parentId);
    CommentPost getCommentPost(String id);

}
