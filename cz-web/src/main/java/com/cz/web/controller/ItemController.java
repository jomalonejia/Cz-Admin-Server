package com.cz.web.controller;

import com.cz.api.service.IItemImagesService;
import com.cz.api.service.IItemService;
import com.cz.core.util.qiniu.PictureUtil;
import com.cz.model.Item;
import com.cz.dto.item.ItemContent;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by jomalone_jia on 2017/9/20.
 */
@RestController
@RequestMapping("/item")
@Api(value = "/item", description = "Item Controller")
public class ItemController {

    private static Logger _log = LoggerFactory.getLogger(ItemController.class);

    @Autowired
    private IItemService itemService;
    @Autowired
    private IItemImagesService itemImagesService;

    @GetMapping("/list/{pageNum}")
    @ApiOperation(value = "item list")
    public Object list(@PathVariable Integer pageNum){
        try {
            PageInfo<Item> itemPageInfo = itemService.listItems(pageNum);
            return itemPageInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("list items failed");
    }


    @PostMapping("/add")
    @ApiOperation(value = "item add")
    public Object add(@RequestBody Item itemObj) {
        try {
            Item item = new Item();
            item.setImage("");
            item.setDescribe("");
            item.setName("alubabaaba");
            boolean insert = itemService.insert(item);
            _log.info("______________");
            _log.info(item.getItemId());
           /* Item item = (Item) itemObj.clone();
            itemService.insert(item);
            _log.info(item.getItemId());
            itemImagesService.insertImages(item.getItemId());*/
            return ResponseEntity.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("list items failed");
    }

    @DeleteMapping("/delete/{itemId}")
    @ApiOperation(value = "item delete")
    public Object delete(@PathVariable String itemId){
        _log.info(itemId);
        try {
            itemService.deleteById(itemId);
            return ResponseEntity.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("delete items failed");
    }

    @PostMapping("/update")
    @ApiOperation(value = "item update")
    public Object update(@RequestBody Item item) {
        try {
            itemService.updateById(item);
            return ResponseEntity.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("list items failed");
    }

    @PostMapping("/update/{itemId}")
    @ApiOperation(value = "item update image")
    public Object updateImage(@PathVariable("itemId") String itemId, @RequestParam("imageUpload") MultipartFile file) {
        String imageUrl = null;
        try {
            imageUrl = PictureUtil.getInstance().uploadPicture(file);
            Integer integer = itemService.updateImageById(itemId, imageUrl);
            return ResponseEntity.ok(imageUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("list items failed");
    }

    @PostMapping("/images/update/{itemId}/{position}")
    @ApiOperation(value = "item update images")
    public Object updateImages(@PathVariable("itemId") String itemId,@PathVariable("position") Integer position, @RequestParam("imageUpload") MultipartFile file) {
        String imageUrl = null;
        try {
            imageUrl = PictureUtil.getInstance().uploadPicture(file);
            return "http://otlht2gvo.bkt.clouddn.com/Fmu7GpZUUw8t8xAZXp5zHBUezmct";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("list items failed");
    }

    @GetMapping("/images/select/{itemId}")
    @ApiOperation(value = "item images select")
    public Object selectItemImages(@PathVariable("itemId") String itemId){
        _log.info(itemId);
        try {
            List<String> images = itemService.selectImages(itemId);
            _log.info(images.toString());
            return images;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("select items images failed");
    }

    @PostMapping("/content/add")
    @ApiOperation(value = "item content add")
    public Object addContent(@RequestBody ItemContent itemContent) throws IOException, NoSuchAlgorithmException {
        String s = itemService.saveOrUpdateItemContent(itemContent);
        return s;
    }



}

