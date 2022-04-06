package com.lei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lei.domain.ResponseResult;
import com.lei.domain.entity.Article;

public interface IArticleService extends IService<Article> {
    ResponseResult hotArticleList();

    ResponseResult articleList(Long categoryId, Integer pageNum, Integer pageSize);

    ResponseResult getArticleDetail(Long id);
}
