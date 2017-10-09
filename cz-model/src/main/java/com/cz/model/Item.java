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
    private String title;
    private String category;
    private Integer price;
    private String mainImage;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
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

}
