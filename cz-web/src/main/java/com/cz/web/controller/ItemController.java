package com.cz.web.controller;

import com.cz.api.service.IItemService;
import com.cz.api.service.IParamService;
import com.cz.common.util.qiniu.PictureUtil;
import com.cz.model.item.Item;
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

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

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
    private IParamService paramService;

    @GetMapping("/list")
    @ApiOperation(value = "item list")
    public Object list(@RequestParam(defaultValue = "1",value = "pageNum") Integer pageNum,
                       @RequestParam(defaultValue = "10",value = "pageSize") Integer pageSize
                       ){
        try {
            PageInfo<Item> itemPageInfo = itemService.listItems(pageNum,pageSize);
            return itemPageInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("list items failed");
    }

    @GetMapping("/list/{categoryId}")
    @ApiOperation(value = "item list by category")
    public Object listByCategory(@PathVariable("categoryId") Integer categoryId,
                                 @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,
                                 @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize){
        try {
            PageInfo<Item> itemPageInfo = itemService.listItemsByCategory(categoryId,pageNum);
            return itemPageInfo;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("list items failed");
    }


    @PostMapping("/add")
    @ApiOperation(value = "item add")
    public Object add(@RequestBody Item item) {
        try {
            itemService.insertItem(item);
            return ResponseEntity.ok();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("list items failed");
    }

    @DeleteMapping("/delete/{itemId}")
    @ApiOperation(value = "item delete")
    public Object delete(@PathVariable String itemId){
        try {
            itemService.deleteItemWithParamById(itemId);
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
            itemService.updateItem(item);
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

    @GetMapping("/images/select/{itemId}")
    @ApiOperation(value = "item images select")
    public Object selectItemByCategory(@PathVariable("itemId") String itemId){
        try {
            return itemService.selectImages(itemId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("select items images failed");
    }

    @PostMapping("/images/update/{itemId}/{position}")
    @ApiOperation(value = "item update images")
    public Object updateImages(@PathVariable("itemId") String itemId,@PathVariable("position") Integer position, @RequestParam("imageUpload") MultipartFile file) {
        String imageUrl = null;
        try {
            imageUrl = PictureUtil.getInstance().uploadPicture(file);
            itemService.updateImages(itemId, imageUrl, position);
            return imageUrl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("list items failed");
    }

    @PostMapping("/content/update")
    @ApiOperation(value = "item content update")
    public ResponseEntity<?> addContent(@RequestBody ItemContent itemContent) throws IOException, NoSuchAlgorithmException {
        try {
            itemService.saveOrUpdateItemContent(itemContent);
            return ResponseEntity.ok().body("update content success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("update content failed");
    }

}

