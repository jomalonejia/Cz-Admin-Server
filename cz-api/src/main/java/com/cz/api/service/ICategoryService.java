package com.cz.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.cz.model.category.Category;

import java.util.List;


/**
 * Created by jomalone_jia on 2017/6/20.
 */
public interface ICategoryService extends IService<Category>{
    List<Category> listCategories();
    List<Category> listCategoriesDesc();
    List<Category> listParentCategories();
    List<Category> listChildCategories(Long parentCategoryId);
    int insertCategory(Category category);
    List<?> listTreeCategories();
}
