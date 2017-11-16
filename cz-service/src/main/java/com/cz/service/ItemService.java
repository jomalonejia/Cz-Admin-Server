package com.cz.service;

import com.cz.api.service.IItemService;
import com.cz.api.service.IParamService;
import com.cz.core.util.constant.QiniuConstant;
import com.cz.core.util.qiniu.PictureUtil;
import com.cz.core.base.BaseServiceImpl;
import com.cz.mapper.ItemImagesMapper;
import com.cz.mapper.ItemMapper;
import com.cz.mapper.ParamMapper;
import com.cz.model.item.Item;
import com.cz.dto.item.ItemContent;
import com.cz.model.item.ItemImages;
import com.cz.model.param.Param;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by jomalone_jia on 2017/6/20.
 */
@Service
@CacheConfig(cacheNames = "item_cache")
public class ItemService extends BaseServiceImpl<ItemMapper, Item> implements IItemService {

    private static final Logger _log = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemImagesMapper itemImagesMapper;
    @Autowired
    private ParamMapper paramMapper;

    @Override
    @Transactional
    public String saveOrUpdateItemContent(ItemContent itemContent) {
        Pattern pattern = Pattern.compile("<img src=\"(.*?)\">");
        Matcher m = pattern.matcher(itemContent.getItemConent());
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String imageUrl = QiniuConstant.QINIU_BASE_URL + PictureUtil.getInstance().uploadPicture(m.group(1));
            String imageTag = "<img src=\"" + imageUrl + "\">";
            m.appendReplacement(sb, imageTag);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    @Override
    public PageInfo<Item> listItems(int pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Item> items = itemMapper.listItems();
        PageInfo<Item> pageInfo = new PageInfo<>(items);
        return pageInfo;
    }

    @Override
    public PageInfo<Item> listItemsByCategory(int categoryId, int pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Item> items = itemMapper.listItemsByCategory(categoryId);
        PageInfo<Item> pageInfo = new PageInfo<>(items);
        return pageInfo;
    }


    @Override
    public Integer updateImageById(String itemId, String imageUrl) {
        return itemMapper.updateImageById(itemId, imageUrl);
    }

    @Override
    @Transactional
    public void insertItem(Item item) {
        item.setImage(QiniuConstant.QINIU_DEFAULT_URL);
        itemMapper.insert(item);
        for (Param param : item.getParams()) {
            paramMapper.insertParams(item.getId(), param.getId(), param.getParamDetails());
        }
        for (int i = 0; i < 6; i++) {
            ItemImages itemImages = new ItemImages();
            itemImages.setItemId(item.getId());
            itemImages.setPosition(i);
            itemImages.setUrl("");
            itemImagesMapper.insert(itemImages);
        }
    }

    @Override
    public void updateItem(Item item) {
        itemMapper.updateById(item);
        paramMapper.deleteParamsById(item.getId());
        for (Param param : item.getParams()) {
            paramMapper.insertParams(item.getId(), param.getId(), param.getParamDetails());
        }
    }

    @Override
    public Integer deleteItemWithParamById(String itemId) {
        return itemMapper.deleteItemWithParamById(itemId);
    }

    public Integer updateItemImages(String itemId, Integer position, String imageUrl) {
        return null;
    }

}