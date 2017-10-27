package com.cz.model.item;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.cz.model.category.Category;
import com.cz.model.param.Param;

import java.io.Serializable;
import java.util.List;


/**
 * Created by jomalone_jia on 2017/9/15.
 */
@TableName("sys_item")
public class Item implements Serializable,Cloneable{

    private static final long serialVersionUID = 1L;

    @TableId
    private String id;
    private String name;
    @TableField(value = "category_id")
    private Long categoryId;
    private Integer price;
    private String image;
    @TableField(exist = false)
    private Category category;
    @TableField(exist = false)
    private List<String> images;
    @TableField(exist = false)
    private List<Param> params;
    private String describe;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Param> getParams() {
        return params;
    }

    public void setParams(List<Param> params) {
        this.params = params;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", category=" + category +
                ", images=" + images +
                ", params=" + params +
                ", describe='" + describe + '\'' +
                '}';
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
