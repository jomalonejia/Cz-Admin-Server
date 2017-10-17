package com.cz.service;

import com.cz.api.service.IItemService;
import com.cz.core.util.constant.QiniuConstant;
import com.cz.core.util.qiniu.PictureUtil;
import com.cz.core.base.BaseServiceImpl;
import com.cz.dto.item.ItemImagesDto;
import com.cz.mapper.ItemMapper;
import com.cz.model.Item;
import com.cz.dto.item.ItemContent;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

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

    @Override
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
    public List<String> selectImages(String itemId) {
        return itemMapper.selectImages(itemId);
    }
}