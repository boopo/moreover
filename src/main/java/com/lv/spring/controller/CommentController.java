package com.lv.spring.controller;

import com.lv.spring.entity.CommentPost;
import com.lv.spring.service.CommentService;
import com.lv.spring.vo.ResultVO;
import io.swagger.models.auth.In;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.*;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/parents/{postId}/{page}/{limit}")
    public ResultVO getCommentParent(@PathVariable String postId,
                                     @PathVariable Integer page,
                                     @PathVariable Integer limit ){

        return ResultVO.ok(commentService.getCommentParentsByPage(postId, page, limit));
    }

    @GetMapping("/children/{postId}/{parentId}")
    public ResultVO getChildren(@PathVariable String postId,
                                @PathVariable String parentId){
        return ResultVO.ok(commentService.getCommentChildren(postId, parentId));
    }

    @PutMapping("/star/{commentId}")
    public ResultVO startComment(@PathVariable String commentId){
        return ResultVO.ok();
    }

    @PutMapping("/unstar/{commentId}")
    public ResultVO unstarComment(@PathVariable String commentId){
        return ResultVO.ok();
    }

    @GetMapping("/star/{commentId}")
    public ResultVO startlist(@PathVariable String commentId ) {
        return ResultVO.ok();
    }

    @PostMapping("/post/{postId}")
    public ResultVO postComment(@PathVariable String postId,
                                @RequestBody @Validated CommentPost commentPost){

        return ResultVO.ok(commentService.commit(commentPost.getMessage(),postId));
    }

    @PutMapping("/post/{postId}/{parentId}")
    public ResultVO postParents(@PathVariable String postId,
                                @PathVariable String parentId,
                                @RequestBody @Validated CommentPost commentPost) {

        return ResultVO.ok(commentService.commit(commentPost.getMessage(), postId, parentId));
    }



}
