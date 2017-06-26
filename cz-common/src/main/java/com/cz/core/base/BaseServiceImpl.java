package com.cz.core.base;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

/**
 * Created by admin on 2017/6/20.
 */
public class BaseServiceImpl<M extends BaseMapper<T>,T> extends ServiceImpl<M,T>{
}
