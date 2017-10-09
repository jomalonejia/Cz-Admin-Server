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

    private static final long serialVersionUID = 1L;

    @TableId
    private Long id;
    @TableField(value = "category_name")
    private String categoryName;
    @TableField(value = "parent_id")
    private Long parentId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }



    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}