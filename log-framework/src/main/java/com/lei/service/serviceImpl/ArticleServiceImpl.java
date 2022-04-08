package com.lei.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lei.config.constants.SystemConstants;
import com.lei.domain.ResponseResult;
import com.lei.domain.entity.Article;
import com.lei.domain.entity.Category;
import com.lei.domain.vo.ArticleDetailVo;
import com.lei.domain.vo.ArticleListVo;
import com.lei.domain.vo.PageVo;
import com.lei.domain.vo.hotArticleVo;
import com.lei.mapper.ArticleMapper;
import com.lei.service.CategoryService;
import com.lei.service.IArticleService;
import com.lei.utils.BeanCopyUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {


//    @Autowired
//    private BeanCopyUtils beanCopyUtils;

    @Autowired
    private CategoryService categoryService;

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

    /**
     * 分类中文章列表的查询
     * @param categoryId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResponseResult articleList(Long categoryId, Integer pageNum, Integer pageSize) {
        //查询条件
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //如果由categoryId,查询时就要和传入的id相同
        queryWrapper.eq(Objects.nonNull(categoryId) && categoryId > 0,Article::getCategoryId,categoryId);
        //必须是正式发布的文章
        queryWrapper.eq(Article::getStatus,SystemConstants.ARTICLE_STATUS_NORMAL);
        //对isTop进行降序
        queryWrapper.orderByDesc(Article::getIsTop);
        //分页查询
        Page<Article> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);

        List<Article> articles = page.getRecords();
        //查询categoryName,获取分类id，查询分类信息，获取分类名称,将分类名称设置给article
        articles.stream()
                .map(article -> article.setCategoryName(categoryService.getById(article.getCategoryId()).getName()))
                .collect(Collectors.toList());
        //使用articleId查询categoryId
//        for(Article article : articles){
//            Category category = categoryService.getById(article.getCategoryId());
//            article.setCategoryName(category.getName());
//        }
        //封装结果
        List<ArticleListVo> articleListVos = BeanCopyUtils.copyBeanList(page.getRecords(), ArticleListVo.class);


        PageVo pageVo = new PageVo(articleListVos,page.getTotal());
        return ResponseResult.okResult(pageVo);
    }

    /**
     * 获取文章详情
     * @param id
     * @return
     */
    @Override
    public ResponseResult getArticleDetail(Long id) {
        //根据id查询文章
        Article article = getById(id);
        //转换成vo
        ArticleDetailVo articleDetailVo = BeanCopyUtils.copyBean(article, ArticleDetailVo.class);
        //根据分类id查询分类名
        Long categoryId = articleDetailVo.getCategoryId();
        Category category = categoryService.getById(categoryId);
        if(category != null){
            articleDetailVo.setCategoryName(category.getName());
        }

        //封装响应返回
        return ResponseResult.okResult(articleDetailVo);
    }
}
