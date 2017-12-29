package com.cz.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.cz.dto.category.CategoryDto;
import com.cz.model.category.Category;

import java.io.Serializable;
import java.util.List;


/**
 * Created by jomalone_jia on 2017/6/20.
 */
public interface ICategoryService extends IService<Category>{
    int insertCategory(Category category);
    int updateCategory(Category category);
    int deleteCategoryById(Serializable id);
    List<Category> listCategories();
    List<Category> listCategoriesDesc();
    List<Category> listParentCategories();
    List<Category> listChildCategories(Long parentCategoryId);
    List<CategoryDto> listTreeCategories();
}
