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
        return categoryService.listTreeCategories();
    }

    @GetMapping("/listCategoriesDesc")
    public Object listCategoriesDesc() {
        List<Category> categories = categoryService.listCategoriesDesc();
        return categories;
    }

    @GetMapping("/listParentCategories")
    public Object listParentCategories() {
        List<Category> parentCategories = categoryService.listParentCategories();
        return parentCategories;
    }

    @GetMapping("/listChildCategories/{parentId}")
    public Object listChildCategories(@PathVariable long parentId) {
        List<Category> childCategories = categoryService.listChildCategories(parentId);
        return childCategories;
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
            categoryService.insertCategory(category);
            return ResponseEntity.ok().body("add success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("add failed");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable long id){
        _log.info(id+"");
        try {
            categoryService.deleteById(id);
            return ResponseEntity.ok().body("delete success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("add failed");
    }
}