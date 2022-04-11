package com.lei.controller;

import com.lei.domain.ResponseResult;
import com.lei.service.CommentService;
import com.lei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;



    /**
     * 获取评论列表
     * @param articleId 文章id
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return
     */
    @GetMapping("/commentList")
    public ResponseResult commentList(Long articleId,Integer pageNum,Integer pageSize){
            return commentService.commentList(articleId,pageNum,pageSize);
    }
}
