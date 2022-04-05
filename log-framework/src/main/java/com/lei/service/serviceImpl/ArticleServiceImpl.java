package com.lei.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lei.config.constants.SystemConstants;
import com.lei.domain.ResponseResult;
import com.lei.domain.entity.Article;
import com.lei.domain.vo.hotArticleVo;
import com.lei.mapper.ArticleMapper;
import com.lei.service.IArticleService;
import com.lei.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {


//    @Autowired
//    private BeanCopyUtils beanCopyUtils;

    /**
     * 查询热门文章，封装层responseResult返回
     * 查询浏览量最高的前10篇文章，展示文章标题和浏览量
     * 注意事项：不能把草稿展示出来，不能把删除的文章展示出来，要求按照浏览量进行降序排序
     * @return
     */
    @Override
    public ResponseResult hotArticleList() {
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //必须是正式文章,文章状态必须是0
        queryWrapper.eq(Article::getStatus, SystemConstants.ARTICLE_STATUS_NORMAL);

        //按照浏览量进行排序
        queryWrapper.orderByDesc(Article::getViewCount);
        //最多只查询10条
        Page<Article> page = new Page<>(1,10);
        page(page,queryWrapper);

        List<Article> articles = page.getRecords();

        //bean拷贝
        List<hotArticleVo> vs = BeanCopyUtils.copyBeanList(articles, hotArticleVo.class);


        return ResponseResult.okResult(vs);
    }
}
