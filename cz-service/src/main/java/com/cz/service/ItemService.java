package com.cz.service;

import com.cz.api.service.IItemService;
import com.cz.core.base.BaseServiceImpl;
import com.cz.item.ItemContent;
import com.cz.mapper.ItemMapper;
import com.cz.model.Item;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by jomalone_jia on 2017/6/20.
 */
@Service
@CacheConfig(cacheNames = "Itemcache")
public class ItemService extends BaseServiceImpl<ItemMapper,Item> implements IItemService {


    @Override
    public void saveOrUpdateItemContent(ItemContent itemContent) {
        Pattern pattern = Pattern.compile( "<img src=\"(.*?)\">" );
        Matcher m = pattern.matcher( itemContent.getItemConent());
        while(m.find()){

        }
    }
}