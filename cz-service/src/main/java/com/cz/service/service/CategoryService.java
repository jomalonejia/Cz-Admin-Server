package com.cz.service.service;

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
    @CzRedisCache(type = List.class)
    //@Cacheable(value = CacheConstant.CACHE_NAMESPACE + "listCategories")
    public List<Category> listCategories() {
        return categoryMapper.listCategories();
    }

    @Override
    @CzRedisCache(type = List.class)
    //@Cacheable(value = CacheConstant.CACHE_NAMESPACE + "listCategoriesDesc")
    public List<Category> listCategoriesDesc() {

        return categoryMapper.listCategoriesDesc();
    }

    @Override
    @CzRedisCache(type = List.class)
    //@Cacheable(value = CacheConstant.CACHE_NAMESPACE + "listParentCategories")
    public List<Category> listParentCategories() {
        return categoryMapper.listParentCategories();
    }

    @Override
    @CzRedisCache(type = List.class)
    //@Cacheable(value = CacheConstant.CACHE_NAMESPACE + "listChildCategories")
    public List<Category> listChildCategories(Long parentCategoryId) {
        return categoryMapper.listChildCategories(parentCategoryId);
    }

    @Override
    @CzRedisEvict(type = List.class)
   /* @CacheEvict(value = {
            CacheConstant.CACHE_NAMESPACE + "listCategories",
            CacheConstant.CACHE_NAMESPACE + "listCategoriesDesc",
            CacheConstant.CACHE_NAMESPACE + "listChildCategories",
            CacheConstant.CACHE_NAMESPACE + "listTreeCategories"})*/
    public int insertCategory(Category category) {
        return categoryMapper.insertCategory(category);
    }

    @Override
    @CzRedisCache(type = List.class)
    /*@Cacheable(value = CacheConstant.CACHE_NAMESPACE + "listTreeCategories")*/
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