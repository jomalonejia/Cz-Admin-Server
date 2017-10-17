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
    List<String> selectImages(String itemId);
}
