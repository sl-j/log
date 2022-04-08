package com.lei.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lei.domain.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户表(User)表数据库访问层
 *
 * @author makejava
 * @since 2022-04-07 10:01:31
 */
//@Mapper
public interface UserMapper extends BaseMapper<User> {

}
