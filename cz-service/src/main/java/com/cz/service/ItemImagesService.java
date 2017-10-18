package com.cz.service;

import com.cz.api.service.IItemImagesService;
import com.cz.core.base.BaseServiceImpl;
import com.cz.mapper.ItemImagesMapper;
import com.cz.model.ItemImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


/**
 * Created by jomalone_jia on 2017/10/18.
 */
@Service
@CacheConfig(cacheNames = "item_images_cache")
public class ItemImagesService extends BaseServiceImpl<ItemImagesMapper,ItemImages> implements IItemImagesService {

    @Autowired
    private ItemImagesMapper itemImagesMapper;

    public void insertImages(String itemId){
        for (int i = 0;i<6;i++) {
            ItemImages itemImages = new ItemImages();
            itemImages.setItemId(itemId);
            itemImages.setPosition(i);
            itemImages.setUrl("");
            itemImagesMapper.insert(itemImages);
        }
    }
}