package com.cz.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.cz.model.ItemImages;


/**
 * Created by jomalone_jia on 2017/10/18.
 */
public interface IItemImagesService extends IService<ItemImages>{
    void insertImages(String itemId);
}
