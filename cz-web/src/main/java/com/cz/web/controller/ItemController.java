package com.cz.web.controller;

import com.cz.api.service.ICategoryService;
import com.cz.api.service.IItemService;
import com.cz.item.DtoCategory;
import com.cz.item.ItemContent;
import com.cz.model.Category;
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
    @Autowired
    private ICategoryService categoryService;

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

    @GetMapping("/listCategories")
    public Object getCategories() {
        List<Category> categories = categoryService.listCategories();
        List cats = new ArrayList<DtoCategory>();
        DtoCategory dtoCategory;
        for (Category category : categories) {
            if (category.getParentId() == 0) {
                dtoCategory = new DtoCategory();
                dtoCategory.setId(category.getId());
                dtoCategory.setName(category.getCategoryName());
                cats.add(dtoCategory);
            } else {
                DtoCategory parentCat = (DtoCategory) cats.get(category.getParentId() - 1);
                System.out.println("parent---->" + parentCat.toString());
                List<DtoCategory> childCat = new ArrayList<DtoCategory>();
                dtoCategory = new DtoCategory();
                dtoCategory.setId(category.getId());
                dtoCategory.setName(category.getCategoryName());
                childCat.add(dtoCategory);
                parentCat.setChildren(childCat);
            }
        }
        return cats;
    }



    /*@GetMapping("/test")
    public Object test() {
          List<Category> categories = categoryService.listCategories();
        Map cats = new HashMap<Long,HashMap<Long,Category>>();
        for (Category category : categories) {
            if(category.getParentId() == 0 && cats.get(category.getId()) == null){
                cats.put(category.getId(),new HashMap<Long,Category>());
            }else{
                if(cats.get(category.getParentId()) == null){
                    cats.put(category.getParentId(),new HashMap<Long,Category>());
                }
                Map map = (Map) cats.get(category.getParentId());
                map.put(category.getId(),category);
            }
        }

        List<Category> categories = categoryService.listCategories();
        List<Object> cats = new ArrayList<>();
        for (Category category : categories) {
            if(category.getParentId() == 0){
               cats.add(category);
            }else{

            }
        }
        return null;
    }*/


}

