package com.cz.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.List;


/**
 * Created by jomalone_jia on 2017/9/15.
 */
@TableName("sys_item")
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "item_id")
    private String itemId;
    private String name;
    @TableField(value = "category_id")
    private Long categoryId;
    private Integer price;
    private String image;
    @TableField(exist = false)
    private Category category;
    @TableField(exist = false)
    private List<String> showImages;
    @TableField(exist = false)
    private List<String> minusShowImages;
    private String describe;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public List<String> getShowImages() {
        return showImages;
    }

    public void setShowImages(List<String> showImages) {
        this.showImages = showImages;
    }

    public List<String> getMinusShowImages() {
        return minusShowImages;
    }

    public void setMinusShowImages(List<String> minusShowImages) {
        this.minusShowImages = minusShowImages;
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
                "itemId=" + itemId +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                ", price=" + price +
                ", image='" + image + '\'' +
                ", category=" + category +
                ", showImages=" + showImages +
                ", minusShowImages=" + minusShowImages +
                ", describe='" + describe + '\'' +
                '}';
    }
}
