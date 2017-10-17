package com.cz.dto.item;

import java.io.Serializable;

/**
 * Created by jomalone_jia on 2017/9/21.
 */
public class ItemContent implements Serializable{
    private static final long serialVersionUID = -4153955458688371437L;

    public String itemId;
    public String itemContent;

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemConent() {
        return itemContent;
    }

    public void setItemConent(String itemContent) {
        this.itemContent = itemContent;
    }

    @Override
    public String toString() {
        return "ItemContent{" +
                "itemId='" + itemId + '\'' +
                ", itemConent='" + itemContent + '\'' +
                '}';
    }
}
