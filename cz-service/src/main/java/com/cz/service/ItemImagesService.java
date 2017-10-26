package com.cz.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cz.api.service.IItemImagesService;
import com.cz.core.base.BaseServiceImpl;
import com.cz.mapper.ItemImagesMapper;
import com.cz.model.item.ItemImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by jomalone_jia on 2017/10/18.
 */
@Service
@CacheConfig(cacheNames = "item_images_cache")
public class ItemImagesService extends BaseServiceImpl<ItemImagesMapper,ItemImages> implements IItemImagesService {

    @Autowired
    private ItemImagesMapper itemImagesMapper;

    @Override
    public List<ItemImages> selectImages(String itemId) {
        EntityWrapper<ItemImages> ew = new EntityWrapper<ItemImages>();
        ew.eq("item_id", itemId).orderBy("position");
        return itemImagesMapper.selectList(ew);
    }

    @Override
    public Integer updateImages(String itemId, String imageUrl, Integer position) {
        return itemImagesMapper.updateImages(itemId, imageUrl, position);
    }
}