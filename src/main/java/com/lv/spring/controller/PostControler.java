package com.lv.spring.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.lv.spring.annotation.Permission;
import com.lv.spring.entity.Post;
import com.lv.spring.enums.PermissionEnum;
import com.lv.spring.enums.ResultVOEnum;
import com.lv.spring.repository.PostRepository;
import com.lv.spring.service.PostService;
import com.lv.spring.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostControler {

    @Autowired
    PostService postService;

    @PostMapping("/post")
    public ResultVO post(@RequestBody @Validated  Post post){
        postService.save(post);
        return ResultVO.ok();
    }

    @GetMapping("/post")
    public ResultVO getPost() {
        return ResultVO.ok(postService.findAll());
    }

    @PutMapping("post/{id}")
    public ResultVO updatePost(@PathVariable String id,
                               @RequestBody @Validated Post post) {
        postService.update(id, post);
        return ResultVO.ok();
    }
    @GetMapping("/post/{id}")
    public ResultVO getOnePost(@PathVariable String id) {
        return ResultVO.ok(postService.getOnePost(id));
    }

    @DeleteMapping("/post/{id}")
    public ResultVO deletePsot(@PathVariable String id) {
        postService.deleted(id);
        return ResultVO.ok(id);
    }

    @GetMapping("/post/{page}/{limit}")
    public ResultVO getPag(@PathVariable Integer page,
                           @PathVariable Integer limit){
        return ResultVO.ok(postService.findByPage(page,limit));
    }


}
