package com.cz.service;

import com.cz.api.service.IItemService;
import com.cz.core.base.BaseServiceImpl;
import com.cz.mapper.ItemMapper;
import com.cz.model.Item;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


/**
 * Created by jomalone_jia on 2017/6/20.
 */
@Service
@CacheConfig(cacheNames = "Itemcache")
public class ItemService extends BaseServiceImpl<ItemMapper,Item> implements IItemService {

   
}