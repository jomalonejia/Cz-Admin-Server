package com.cz.service;

import com.cz.api.service.ICategoryService;
import com.cz.core.base.BaseServiceImpl;
import com.cz.core.util.constant.CacheConstant;
import com.cz.item.DtoCategory;
import com.cz.mapper.CategoryMapper;
import com.cz.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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
    @Cacheable(value = CacheConstant.CACHE_NAMESPACE + "listCategories")
    public List<Category> listCategories() {
        return categoryMapper.listCategories();
    }

    @Override
    @Cacheable(value = CacheConstant.CACHE_NAMESPACE + "listCategoriesDesc")
    public List<Category> listCategoriesDesc() {

        return categoryMapper.listCategoriesDesc();
    }

    @Override
    @Cacheable(value = CacheConstant.CACHE_NAMESPACE + "listParentCategories")
    public List<Category> listParentCategories() {
        return categoryMapper.listParentCategories();
    }

    @Override
    @Cacheable(value = CacheConstant.CACHE_NAMESPACE + "listChildCategories")
    public List<Category> listChildCategories(Long parentCategoryId) {
        return categoryMapper.listChildCategories(parentCategoryId);
    }

    @Override
    @CacheEvict(value = {
            CacheConstant.CACHE_NAMESPACE + "listCategories",
            CacheConstant.CACHE_NAMESPACE + "listCategoriesDesc",
            CacheConstant.CACHE_NAMESPACE + "listChildCategories",
            CacheConstant.CACHE_NAMESPACE + "listTreeCategories"})
    public int insertCategory(Category category) {
        return categoryMapper.insertCategory(category);
    }

    @Override
    @Cacheable(value = CacheConstant.CACHE_NAMESPACE + "listTreeCategories")
    public List listTreeCategories() {
        List cats = null;
        List<Category> categories = null;
        try {
            categories = categoryMapper.listCategories();
            cats = new ArrayList<DtoCategory>();
            DtoCategory dtoCategory;
            for (Category category : categories) {
                if (category.getParentCategoryId() == 0) {
                    dtoCategory = new DtoCategory();
                    dtoCategory.setId(category.getCategoryId());
                    dtoCategory.setName(category.getCategoryName());
                    cats.add(dtoCategory);
                } else {
                    if (cats.get(category.getParentCategoryId() - 1) == null) {
                        dtoCategory = new DtoCategory();
                        dtoCategory.setId(category.getCategoryId());
                        dtoCategory.setName(category.getCategoryName());
                        cats.add(dtoCategory);
                    } else {
                        DtoCategory parentCat = (DtoCategory) cats.get(category.getParentCategoryId() - 1);
                        List<DtoCategory> childCat = new ArrayList<DtoCategory>();
                        dtoCategory = new DtoCategory();
                        dtoCategory.setId(category.getCategoryId());
                        dtoCategory.setName(category.getCategoryName());
                        childCat.add(dtoCategory);
                        parentCat.setChildren(childCat);
                    }
                }
            }
            _log.info(cats.toString());
            return cats;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}