package com.example.demo.mapper;

import com.example.demo.model.Test;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wanghao
 * @since 2019-07-23
 */
public interface TestMapper extends BaseMapper<Test> {

    List<Test> queryList();
}
