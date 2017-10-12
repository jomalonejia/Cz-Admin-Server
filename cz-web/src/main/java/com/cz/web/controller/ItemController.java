package com.cz.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cz.api.service.ICategoryService;
import com.cz.api.service.IItemService;
import com.cz.item.DtoCategory;
import com.cz.item.ItemContent;
import com.cz.model.Category;
import com.cz.model.Item;
import com.cz.model.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/list")
    @ApiOperation(value = "item list")
    public Object list(){
        EntityWrapper<Item> ew = new EntityWrapper<Item>();
        return itemService.selectList(ew);
    }


    @PostMapping("/add")
    @ApiOperation(value = "item add")
    public Object add(@RequestBody Item item) {
        _log.info(item.getCategoryId()+"");
        boolean insert = itemService.insert(item);
        return "success";
    }

    @PostMapping("/minusImage/update")
    @ApiOperation(value = "item images add")
    public Object profileUpload(@RequestParam("itemImageUpload") MultipartFile file, HttpServletRequest request) {
        _log.info(file.getName());
        _log.info(file.getOriginalFilename());
        _log.info("hehe");
        return "hi";
    }

    @PostMapping("/content/add")
    @ApiOperation(value = "item content add")
    public Object addContent(@RequestBody ItemContent itemContent) throws IOException, NoSuchAlgorithmException {
        String s = itemService.saveOrUpdateItemContent(itemContent);
        return s;
    }

}

