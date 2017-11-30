package com.cz.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cz.dto.item.ItemContent;
import com.cz.model.item.Item;
import org.apache.ibatis.annotations.Param;
import org.springframework.security.access.method.P;

import java.util.List;

/**
 * Created by jomalone_jia on 2017/6/19.
 */
public interface ItemMapper extends BaseMapper<Item> {
    List<Item> listItems();
    List<Item> listItemsByCategory(@Param("categoryId") int categoryId);
    Integer updateImageById(@Param("itemId") String itemId,@Param("imageUrl") String imageUrl);
    Integer updateContentById(@Param("itemContent")ItemContent itemContent);
    Integer deleteItemWithParamById(@Param("itemId") String itemId);
    String getItemContentById(@Param("itemId") String itemId);
    Integer deleteItemContentById(@Param("itemId") String itemId);
}
