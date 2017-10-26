package com.cz.model.item;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * Created by jomalone_jia on 2017/10/18.
 */
@TableName("sys_item_images")
public class ItemImages implements Serializable {
    private static final long serialVersionUID = 6670314819958238171L;

    @TableId
    private String id;
    @TableField("item_id")
    private String itemId;
    private String url;
    private Integer position;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "ItemImages{" +
                "id='" + id + '\'' +
                ", itemId='" + itemId + '\'' +
                ", url='" + url + '\'' +
                ", position=" + position +
                '}';
    }
}