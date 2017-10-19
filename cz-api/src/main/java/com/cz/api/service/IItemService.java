package com.cz.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.cz.model.Item;
import com.cz.dto.item.ItemContent;
import com.github.pagehelper.PageInfo;
import java.util.List;


/**
 * Created by jomalone_jia on 2017/6/20.
 */
public interface IItemService extends IService<Item>{
    String  saveOrUpdateItemContent(ItemContent itemContent);
    PageInfo<Item> listItems(int pageNum);
    PageInfo<Item> listItemsByCategory(int categoryId,int pageNum);
    Integer updateImageById(String itemId, String imageUrl);
    Integer insertItems(Item item);
}
