package com.cz.service;

import com.cz.api.service.IItemService;
import com.cz.core.util.constant.QiniuConstant;
import com.cz.core.util.qiniu.PictureUtil;
import com.cz.core.base.BaseServiceImpl;
import com.cz.mapper.ItemImagesMapper;
import com.cz.mapper.ItemMapper;
import com.cz.model.Item;
import com.cz.dto.item.ItemContent;
import com.cz.model.ItemImages;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Created by jomalone_jia on 2017/6/20.
 */
@Service
@CacheConfig(cacheNames = "item_cache")
public class ItemService extends BaseServiceImpl<ItemMapper,Item> implements IItemService {

    private static final Logger _log = LoggerFactory.getLogger(ItemService.class);

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemImagesMapper itemImagesMapper;

    @Override
    @Transactional
    public String  saveOrUpdateItemContent(ItemContent itemContent) {
        Pattern pattern = Pattern.compile( "<img src=\"(.*?)\">" );
        Matcher m = pattern.matcher( itemContent.getItemConent());
        StringBuffer sb = new StringBuffer();
        while(m.find()){
            String imageUrl = QiniuConstant.QINIU_BASE_URL + PictureUtil.getInstance().uploadPicture(m.group(1));
            String imageTag = "<img src=\""+ imageUrl + "\">";
            m.appendReplacement(sb, imageTag);
        }
        m.appendTail(sb);
        return sb.toString();
    }

    @Override
    public PageInfo<Item> listItems(int pageNum) {
        PageHelper.startPage(pageNum,5);
        List<Item> items = itemMapper.listItems();
        PageInfo<Item> pageInfo =new PageInfo<>(items);
        return pageInfo;
    }

    @Override
    public PageInfo<Item> listItemsByCategory(int categoryId, int pageNum) {
        PageHelper.startPage(pageNum,5);
        List<Item> items = itemMapper.listItemsByCategory(categoryId);
        PageInfo<Item> pageInfo =new PageInfo<>(items);
        return pageInfo;
    }


    @Override
    public Integer updateImageById(String itemId, String imageUrl) {
        return itemMapper.updateImageById(itemId,imageUrl);
    }

    @Override
    public Integer insertItems(Item item) {
        Integer result = 0;
        try {
            item.setImage(QiniuConstant.QINIU_DEFAULT_URL);
            result = itemMapper.insert(item);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            for (int i = 0;i<6;i++) {
                ItemImages itemImages = new ItemImages();
                itemImages.setItemId(item.getItemId());
                itemImages.setPosition(i);
                itemImages.setUrl("");
                itemImagesMapper.insert(itemImages);
            }
        } catch (Exception e) {
            e.printStackTrace();
            result = 0;
        }
        return result;
    }

    public Integer updateItemImages (String itemId,Integer position,String imageUrl){
        return null;
    }

}