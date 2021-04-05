package com.lv.spring.controller;

import com.lv.spring.entity.CommentPost;
import com.lv.spring.service.CommentService;
import com.lv.spring.vo.ResultVO;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    CommentService commentService;

    @GetMapping("/parents/{postId}/{page}/{limit}")
    public ResultVO getCommentParent(@PathVariable String postId,
                                     @PathVariable Integer page,
                                     @PathVariable Integer limit ){
        System.out.println(postId);
        System.out.println(page);
        System.out.println(limit);

        return ResultVO.ok(commentService.getCommentParentsByPage(postId, page, limit));
    }

    @GetMapping("/children/{postId}/{parentId}")
    public ResultVO getChildren(@PathVariable String postId,
                                @PathVariable String parentId){
        return ResultVO.ok(commentService.getCommentChildren(postId, parentId));
    }

    @PutMapping("/star/{commentId}")
    public ResultVO startComment(@PathVariable String commentId){
        boolean  is_star = commentService.star(commentId);
        return is_star?ResultVO.ok():ResultVO.fail();
    }

    @PutMapping("/unstar/{commentId}")
    public ResultVO unstarComment(@PathVariable String commentId){
        boolean is_unstar = commentService.unstar(commentId);
        return is_unstar?ResultVO.ok():ResultVO.fail();
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
