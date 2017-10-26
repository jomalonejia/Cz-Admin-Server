package com.cz.web.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cz.api.service.ICategoryService;
import com.cz.model.category.Category;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/listTreeCategories")
    public Object listTreeCategories() {
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

    @GetMapping("/listChildCategories/{parentCategoryId}")
    public Object listChildCategories(@PathVariable Long parentCategoryId) {
        List<Category> childCategories = categoryService.listChildCategories(parentCategoryId);
        return childCategories;
    }

    @PostMapping("/edit")
    public ResponseEntity<?> edit(@RequestBody Category category){
        try {
            EntityWrapper<Category> ew = new EntityWrapper<Category>();
            ew.eq("id", category.getId());
            categoryService.update(category,ew);
            return ResponseEntity.ok().body("success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("failed");
    }


    @PostMapping("/add")
    public ResponseEntity<?> add(@RequestBody Category category){
        try {
            categoryService.insertCategory(category);
            return ResponseEntity.ok().body("add success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("add failed");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            categoryService.deleteById(id);
            return ResponseEntity.ok().body("delete success");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.badRequest().body("add failed");
    }
}
