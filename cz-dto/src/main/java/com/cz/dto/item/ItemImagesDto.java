package com.cz.dto.item;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jomalone_jia on 2017/10/17.
 */
public class ItemImagesDto implements Serializable{

    private static final long serialVersionUID = 4747799988473778284L;

    private String image;
    private List<String> images;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "ItemImagesDto{" +
                "image='" + image + '\'' +
                ", images=" + images +
                '}';
    }
}
