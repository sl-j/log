package com.lei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lei.domain.ResponseResult;
import com.lei.domain.entity.Comment;


/**
 * 评论表(Comment)表服务接口
 *
 * @author makejava
 * @since 2022-04-11 09:28:05
 */
public interface CommentService extends IService<Comment> {

    ResponseResult commentList(String commentType, Long articleId, Integer pageNum, Integer pageSize);

    ResponseResult addComment(Comment comment);
}
