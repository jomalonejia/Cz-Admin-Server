package com.cz.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cz.model.item.ItemImages;
import org.apache.ibatis.annotations.Param;

/**
 * Created by jomalone_jia on 2017/6/19.
 */
public interface ItemImagesMapper extends BaseMapper<ItemImages> {
    Integer updateImages(@Param("itemId") String itemId, @Param("imageUrl") String imageUrl, @Param("position") Integer position);
}
