package com.lei.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lei.domain.ResponseResult;
import com.lei.domain.entity.Link;


/**
 * 友链(Link)表服务接口
 *
 * @author makejava
 * @since 2022-04-06 11:03:24
 */
public interface LinkService extends IService<Link> {

    ResponseResult getAllLink();
}
