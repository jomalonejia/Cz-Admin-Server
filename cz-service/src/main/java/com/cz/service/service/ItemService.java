package com.cz.service.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cz.api.service.IItemService;
import com.cz.common.util.constant.QiniuConstant;
import com.cz.common.base.BaseServiceImpl;
import com.cz.mapper.ItemImagesMapper;
import com.cz.mapper.ItemMapper;
import com.cz.mapper.ParamMapper;
import com.cz.model.item.Item;
import com.cz.dto.item.ItemContent;
import com.cz.model.item.ItemImages;
import com.cz.model.param.Param;
import com.cz.service.annotation.CzRedisCache;
import com.cz.service.annotation.CzRedisEvict;
import com.cz.service.search.EsClient;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


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
    @CzRedisEvict
    @Transactional
    public void saveOrUpdateItemContent(ItemContent itemContent) {
        /*String content = itemMapper.getItemContentById(itemContent.getItemId());
        if(StringUtils.isNotEmpty(content)){
            Pattern deletePattern = Pattern.compile("<img src=\"http://otlht2gvo.bkt.clouddn.com/(.*?)\">");
            Matcher matcher = deletePattern.matcher(content);
            ArrayList<String> deleteArray = new ArrayList<>();
            while (matcher.find()){
                deleteArray.add(matcher.group(1));
            }
            PictureUtil.getInstance().bucketDelete(deleteArray.toArray(new String[0]));
        }
        itemMapper.deleteItemContentById(itemContent.getItemId());
        Pattern pattern = Pattern.compile("<img src=\"(.*?)\">");
        Matcher m = pattern.matcher(itemContent.getContent());
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            String imageUrl = QiniuConstant.QINIU_BASE_URL + PictureUtil.getInstance().uploadPicture(m.group(1));
            String imageTag = "<img src=\"" + imageUrl + "\">";
            m.appendReplacement(sb, imageTag);
        }
        m.appendTail(sb);*/
        //itemMapper.updateContentById(new ItemContent(itemContent.getItemId(),sb.toString()));
        Item item = itemMapper.selectById(itemContent.getItemId());
        item.setContent(itemContent.getContent());
        itemMapper.updateById(item);
    }

    @Override
    @CzRedisCache(type = PageInfo.class)
    public PageInfo<Item> listItems(int pageNum,int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Item> items = itemMapper.listItems();
        PageInfo<Item> pageInfo = new PageInfo<>(items);
        return pageInfo;
    }

    @Override
    @CzRedisCache(type = PageInfo.class)
    public PageInfo<Item> listItemsByCategory(int categoryId, int pageNum) {
        PageHelper.startPage(pageNum, 5);
        List<Item> items = itemMapper.listItemsByCategory(categoryId);
        PageInfo<Item> pageInfo = new PageInfo<>(items);
        return pageInfo;
    }


    @Override
    @CzRedisEvict
    @Transactional
    public Integer updateImageById(String itemId, String imageUrl) {
        return itemMapper.updateImageById(itemId, imageUrl);
    }

    @Override
    @CzRedisEvict
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
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.submit(()->{
            EsClient.add(item);
        });
        executorService.shutdown();
    }

    @Override
    @CzRedisEvict
    public void updateItem(Item item) {
        itemMapper.updateById(item);
        paramMapper.deleteParamsById(item.getId());
        for (Param param : item.getParams()) {
            paramMapper.insertParams(item.getId(), param.getId(), param.getParamDetails());
        }
    }

    @Override
    @CzRedisEvict
    public Integer deleteItemWithParamById(String itemId) {
        return itemMapper.deleteItemWithParamById(itemId);
    }

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

    @Override
    @CzRedisEvict
    public void test(){
        System.out.println("aa");
    }
}