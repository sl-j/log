package com.lei.config.constants;

public class SystemConstants
{
    /**
     *  文章是草稿
     */
    public static final int ARTICLE_STATUS_DRAFT = 1;
    /**
     *  文章是正常分布状态
     */
    public static final int ARTICLE_STATUS_NORMAL = 0;

    /**
     * 此分类是否被禁用
     */
    public static final String STATUS_NORMAL = "0";

    /**
     * 友链是否被审核通过 0表示通过 1表示未通过 2表示正在审核
     */
    public static final String LINK_STATUS_PASS = "0";


    /**
     * 缓存中浏览量
     */
    public static final String KEY = "article:viewCount";

    public static final String LINK_STATUS_NOPASS = "1";

    public static final String LINK_STATUS_CHECK = "2";
    /**
     * 评论类型：0表示文章评论，1表示友链评论
     */
    public static final String ARTICLE_COMMENT = "0";

    public static final String LINK_COMMENT = "1";
}