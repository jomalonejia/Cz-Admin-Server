package com.cz.service;

import com.cz.api.service.ICategoryService;
import com.cz.core.base.BaseServiceImpl;
import com.cz.mapper.CategoryMapper;
import com.cz.model.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by jomalone_jia on 2017/6/20.
 */
@Service
@CacheConfig(cacheNames = "category_cache")
public class CategoryService extends BaseServiceImpl<CategoryMapper,Category> implements ICategoryService {

    private static final Logger _log = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> listCategories() {
        return categoryMapper.listCategories();
    }

    @Override
    public List<Category> listParentCategories() {
        return categoryMapper.listParentCategories();
    }
}
