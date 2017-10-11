package com.cz.web.controller;

import com.cz.api.service.ICategoryService;
import com.cz.item.DtoCategory;
import com.cz.model.Category;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jomalone_jia on 2017/10/11.
 */
@RestController
@RequestMapping("/category")
@Api(value = "/category",description = "Category Controller")
public class CategoryController {

    private static final Logger _log = LoggerFactory.getLogger(CategoryController.class);

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/listCategories")
    public Object listCategories() {
        List cats = null;
        List<Category> categories;
        try {
            categories = categoryService.listCategories();
            cats = new ArrayList<DtoCategory>();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }

    @GetMapping("/listAllCategories")
    public Object listAllCategories() {
        List<Category> categories = categoryService.listCategories();
        return categories;
    }

    @GetMapping("/listParentCategories")
    public Object listParentCategories() {
        List<Category> categories = categoryService.listParentCategories();
        return categories;
    }

    @PostMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody Category category){
        _log.info(category.toString());
        try {
            boolean b = categoryService.updateById(category);
            if(b){
                return ResponseEntity.ok().body("success");
            }else{
                return ResponseEntity.badRequest().body("failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("failed");
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Category category){
        _log.info(category.toString());
        try {
            boolean b = categoryService.insert(category);
            if(b){
                return ResponseEntity.ok().body("success");
            }else{
                return ResponseEntity.badRequest().body("failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("failed");
    }
}
