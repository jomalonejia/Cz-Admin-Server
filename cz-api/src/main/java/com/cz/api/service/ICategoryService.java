package com.cz.api.service;

import com.baomidou.mybatisplus.service.IService;
import com.cz.model.Category;

import java.util.List;
import java.util.Map;


/**
 * Created by jomalone_jia on 2017/6/20.
 */
public interface ICategoryService extends IService<Category>{
    List<Category> listCategories();
    List<Category> listCategoriesDesc();
    List<Category> listParentCategories();
    List<Category> listChildCategories(Long parentId);
    int insertCategory(Category category);
    List listTreeCategories();
}
