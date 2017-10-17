package com.cz.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cz.model.Item;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jomalone_jia on 2017/6/19.
 */
public interface ItemMapper extends BaseMapper<Item> {
    List<Item> listItems();
    List<String> selectImages(@Param("itemId") String itemId);
}
