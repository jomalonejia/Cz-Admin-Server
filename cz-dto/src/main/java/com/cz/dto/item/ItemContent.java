package com.cz.dto.item;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * Created by jomalone_jia on 2017/9/21.
 */
public class ItemContent implements Serializable{
    private static final long serialVersionUID = -4153955458688371437L;

    @TableField("item_id")
    public String itemId;
    @TableField("item_content")
    public String content;


    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "ItemContent{" +
                "itemId='" + itemId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public ItemContent(){}

    public ItemContent(String itemId, String content) {
        this.itemId = itemId;
        this.content = content;
    }
}
