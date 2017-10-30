package com.cz.service;

import com.cz.api.service.IParamService;
import com.cz.core.base.BaseServiceImpl;
import com.cz.mapper.ParamMapper;
import com.cz.model.param.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public List<Param> listParams() {
        return paramMapper.listParams();
    }

    @Override
    public List<Param> listParamsById(String itemId) {
        return paramMapper.listParamsById(itemId);
    }

    @Override
    public void insertParams(String itemId, List<Param> params) {
        try {
            for (Param param : params) {
                paramMapper.insertParams(itemId,param.getId(),param.getParamValues());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    @Transactional
    public void updateParams(String itemId,List<Param> params){
        paramMapper.deleteParamsById(itemId);
        this.insertParams(itemId, params);
    }
}
