package com.lei.controller;

import com.lei.config.constants.SystemConstants;
import com.lei.domain.ResponseResult;
import com.lei.domain.entity.Comment;
import com.lei.service.CommentService;
import com.lei.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
            return commentService.commentList(SystemConstants.ARTICLE_COMMENT,articleId,pageNum,pageSize);
    }

    /**
     * 友链评论的功能
     *
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping("/linkCommentList")
    public ResponseResult linkCommentList(Integer pageNum,Integer pageSize){
        return commentService.commentList(SystemConstants.LINK_COMMENT,null,pageNum,pageSize);
    }


    @PostMapping()
    public ResponseResult addComment(@RequestBody Comment comment){
        return commentService.addComment(comment);
    }
}
