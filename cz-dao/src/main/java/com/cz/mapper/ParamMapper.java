package com.cz.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.cz.model.param.Param;
import com.cz.model.param.ParamDetail;
import com.cz.model.param.ParamValue;
import com.cz.model.personal.User;

import java.util.List;

/**
 * Created by jomalone_jia on 2017/6/19.
 */
public interface ParamMapper extends BaseMapper<Param>{
    List<Param> listParams();
    List<Param> listParamsById(@org.apache.ibatis.annotations.Param("itemId") String itemId);
    Integer insertParams(@org.apache.ibatis.annotations.Param("itemId") String itemId,
                         @org.apache.ibatis.annotations.Param("paramId") Integer paramId,
                         @org.apache.ibatis.annotations.Param("paramDetails") List<ParamDetail> paramDetails);
    Integer deleteParamsById(@org.apache.ibatis.annotations.Param("itemId") String itemId);
}
