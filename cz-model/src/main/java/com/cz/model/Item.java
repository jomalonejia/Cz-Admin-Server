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

    @TableId
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public void setMainImage(String image) {
        this.image = image;
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
                "id=" + id +
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
