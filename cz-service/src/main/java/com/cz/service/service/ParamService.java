package com.cz.service.service;

import com.cz.api.service.IParamService;
import com.cz.common.base.BaseServiceImpl;
import com.cz.mapper.ParamMapper;
import com.cz.model.param.Param;
import com.cz.service.annotation.CzRedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by jomalone_jia on 2017/10/26.
 */
@Service
@CacheConfig(cacheNames = "param_cache")
public class ParamService extends BaseServiceImpl<ParamMapper,Param> implements IParamService {

    private static Logger _log = LoggerFactory.getLogger(ParamService.class);

    @Autowired
    private ParamMapper paramMapper;

    @Override
    @CzRedisCache(type = Param.class)
    public List<Param> listParams() {
        return paramMapper.listParams();
    }

    @Override
    @CzRedisCache(type = Param.class)
    public List<Param> listParamsById(String itemId) {
        return paramMapper.listParamsById(itemId);
    }
}
