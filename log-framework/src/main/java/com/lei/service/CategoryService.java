package com.lei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lei.domain.ResponseResult;
import com.lei.domain.entity.Category;


/**
 * 分类表(Category)表服务接口
 *
 * @author makejava
 * @since 2022-04-05 10:04:34
 */
public interface CategoryService extends IService<Category> {

    ResponseResult getCategoryList();
}
