package com.cz.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.cz.model.param.Param;

import java.util.List;

/**
 * Created by jomalone_jia on 2017/10/26.
 */
public interface IParamService extends IService<Param> {
    List<Param> listParams();
    List<Param> listParamsById(String itemId);
    void insertParams(String itemId,List<Param> params);
    void updateParams(String itemId,List<Param> params);
}
