package com.cz.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cz.model.personal.UserRelationship;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jomalone_jia on 2017/8/8.
 */
public interface UserRelationshipMapper extends BaseMapper<UserRelationship> {
    List<Long> listRelatedId(@Param("userId") Long userId);
}
