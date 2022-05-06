package com.lei.job;

import com.lei.config.constants.SystemConstants;
import com.lei.domain.entity.Article;
import com.lei.service.IArticleService;
import com.lei.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class UpdateViewCountJob {

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private IArticleService articleService;

    /**
     * 定时任务，每隔一段时间就进行redis和mysql中的同步
     */
    @Scheduled(cron = "0/5 * * * * ?")
    public void updateViewCount(){
        //获取redis中的浏览量
        Map<String, Integer> cacheMap = redisCache.getCacheMap(SystemConstants.KEY);

        List<Article> articles = cacheMap.entrySet()
                .stream()
                .map(stringIntegerEntry -> new Article(Long.valueOf(stringIntegerEntry.getKey()), stringIntegerEntry.getValue().longValue()))
                .collect(Collectors.toList());
        //更新到数据库中
        articleService.updateBatchById(articles);
    }
}
