package com.cz.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.List;


/**
 * Created by jomalone_jia on 2017/9/15.
 */
@TableName("sys_category")
public class Category implements Serializable {

    private static final long serialVersionUID = -1425330621990331322L;

    @TableId(value = "category_id")
    private Long categoryId;
    @TableField(value = "category_name")
    private String categoryName;
    @TableField(value = "parent_category_id")
    private Integer parentCategoryId;

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryName='" + categoryName + '\'' +
                ", parentCategoryId=" + parentCategoryId +
                '}';
    }
}
