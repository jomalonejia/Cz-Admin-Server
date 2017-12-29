package com.cz.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.cz.model.item.Item;
import com.cz.dto.item.ItemContent;
import com.cz.model.item.ItemImages;
import com.github.pagehelper.PageInfo;

import java.util.List;


/**
 * Created by jomalone_jia on 2017/6/20.
 */
public interface IItemService extends IService<Item>{
    void saveOrUpdateItemContent(ItemContent itemContent);
    PageInfo<Item> listItems(int pageNum,int pageSize);
    PageInfo<Item> listItemsByCategory(int categoryId,int pageNum);
    Integer updateImageById(String itemId, String imageUrl);
    void insertItem(Item item);
    void updateItem(Item item);
    Integer deleteItemWithParamById(String itemId);
    Integer updateImages(String itemId,String imageUrl,Integer position);
    List<ItemImages> selectImages(String itemId);
    void test();
}
