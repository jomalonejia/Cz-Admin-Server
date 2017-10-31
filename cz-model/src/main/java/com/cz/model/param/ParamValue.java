package com.cz.model.param;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * Created by jomalone_jia on 2017/10/26.
 */
@TableName("sys_param_value")
public class ParamValue implements Serializable{

    private static final long serialVersionUID = 483634340243752463L;

    @TableField("param_id")
    private Integer paramId;
    @TableField("param_value")
    private String paramValue;


    public Integer getParamId() {
        return paramId;
    }

    public void setParamId(Integer paramId) {
        this.paramId = paramId;
    }

    public String getParamValue() {
        return paramValue;
    }

    public void setParamValue(String paramValue) {

        this.paramValue = paramValue;
    }

    public ParamValue(){}

    public ParamValue(Integer id, String paramValue) {
        this.paramValue = paramValue;
    }


    @Override
    public String toString() {
        return "ParamValue{" +
                ", paramId=" + paramId +
                ", paramValue='" + paramValue + '\'' +
                '}';
    }
}
