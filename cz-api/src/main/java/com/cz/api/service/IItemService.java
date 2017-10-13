package com.cz.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.cz.item.ItemContent;
import com.cz.model.Item;

import java.util.List;


/**
 * Created by jomalone_jia on 2017/6/20.
 */
public interface IItemService extends IService<Item>{
    String  saveOrUpdateItemContent(ItemContent itemContent);
    List<Item> listItems();
}
