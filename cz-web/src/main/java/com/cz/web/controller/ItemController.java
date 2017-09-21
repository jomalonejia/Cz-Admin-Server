package com.cz.web.controller;

import com.cz.core.util.qiniu.PictureUtil;
import com.cz.item.ItemContent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by jomalone_jia on 2017/9/20.
 */
@RestController
@RequestMapping("/item")
@Api(value = "/item",description = "Item Controller")
public class ItemController {

    private static Logger _log = LoggerFactory.getLogger(ItemController.class);

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
    public Object addContent(@RequestBody ItemContent itemContent) {
        _log.info(itemContent.toString());
        _log.info("hehe");
        return "hi";
    }
}
