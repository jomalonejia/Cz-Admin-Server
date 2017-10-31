package com.cz.model.param;

import java.io.Serializable;

/**
 * Created by jomalone_jia on 2017/10/31.
 */
public class ParamDetail implements Serializable{

    private static final long serialVersionUID = 1L;

    private String paramValue;
    private String inventory;

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return "ParamDetail{" +
                "paramValue='" + paramValue + '\'' +
                ", inventory='" + inventory + '\'' +
                '}';
    }
}
