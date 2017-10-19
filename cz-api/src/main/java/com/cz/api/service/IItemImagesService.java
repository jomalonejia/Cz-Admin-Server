package com.cz.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.cz.model.ItemImages;

import java.util.List;


/**
 * Created by jomalone_jia on 2017/10/18.
 */
public interface IItemImagesService extends IService<ItemImages>{
    Integer updateImages(String itemId,String imageUrl,Integer position);
    List<ItemImages> selectImages(String itemId);
}
