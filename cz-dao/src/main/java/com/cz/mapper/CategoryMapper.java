package com.cz.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.cz.model.category.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by jomalone_jia on 2017/6/19.
 */
public interface CategoryMapper extends BaseMapper<Category> {
    List<Category> listCategories();
    List<Category> listCategoriesDesc();
    List<Category> listParentCategories();
    List<Category> listChildCategories(@Param(value = "parentId") Long parentId);
    Integer insertCategory(Category category);
}
