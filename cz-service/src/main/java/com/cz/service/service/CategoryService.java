package com.cz.service.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.cz.api.service.ICategoryService;
import com.cz.common.base.BaseServiceImpl;
import com.cz.mapper.CategoryMapper;
import com.cz.model.category.Category;
import com.cz.dto.category.CategoryDto;
import com.cz.service.annotation.CzRedisCache;
import com.cz.service.annotation.CzRedisEvict;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by jomalone_jia on 2017/6/20.
 */
@Service
@CacheConfig(cacheNames = "category_cache")
public class CategoryService extends BaseServiceImpl<CategoryMapper, Category> implements ICategoryService {

    private static final Logger _log = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    @CzRedisCache(type = Category.class)
    public List<Category> listCategories() {
        return categoryMapper.listCategories();
    }

    @Override
    @CzRedisCache(type = Category.class)
    public List<Category> listCategoriesDesc() {

        return categoryMapper.listCategoriesDesc();
    }

    @Override
    @CzRedisCache(type = Category.class)
    public List<Category> listParentCategories() {
        return categoryMapper.listParentCategories();
    }

    @Override
    @CzRedisCache(type = Category.class)
    public List<Category> listChildCategories(Long parentCategoryId) {
        return categoryMapper.listChildCategories(parentCategoryId);
    }

    @Override
    @CzRedisEvict
    public int insertCategory(Category category) {
        return categoryMapper.insertCategory(category);
    }

    @Override
    @CzRedisEvict
    public int updateCategory(Category category) {
        EntityWrapper<Category> ew = new EntityWrapper<Category>();
        ew.eq("id", category.getId());
        return categoryMapper.update(category,ew);
    }

    @Override
    @CzRedisEvict
    public int deleteCategoryById(Serializable id) {
        return categoryMapper.deleteById(id);
    }

    @Override
    @CzRedisCache(type = CategoryDto.class)
    public List<CategoryDto> listTreeCategories() {

        //this method would be modified later ............

        List<CategoryDto> cats = null;
        List<Category> categories = null;
        try {
            categories = categoryMapper.listCategories();
            _log.info(categories.toString());
            cats = new ArrayList<>();
            CategoryDto categoryDto;
            for (Category category : categories) {
                if (category.getParentId() == 0) {
                    categoryDto = new CategoryDto(
                            category.getId(),
                            category.getName(),
                            new ArrayList<CategoryDto>());
                    cats.add(categoryDto);
                } else {
                    CategoryDto parentCategory = cats.get(category.getParentId() - 1);
                    parentCategory.getChildren().add(new CategoryDto(category.getId(),category.getName()));
                }
            }
            return cats;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}