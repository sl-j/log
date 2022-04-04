package com.lei.controller;


import com.lei.domain.ResponseResult;
import com.lei.domain.entity.Article;
import com.lei.service.IArticleService;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private IArticleService articleService;

//    @GetMapping("/list")
//    public List<Article> test(){
//        return articleService.list();
//    }


    @GetMapping("/hotArticleList")
    public ResponseResult hotArticleList(){
        //查询热门文章，封装层responseResult返回
        ResponseResult result = articleService.hotArticleList();
        return result;
    }
}
