package com.cz.model.param;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jomalone_jia on 2017/10/26.
 */
@TableName("sys_param")
public class Param implements Serializable{

    private static final long serialVersionUID = -392251045973881802L;

    @TableId
    private Integer id;
    @TableField("param_name")
    private String paramName;
    @TableField("param_describe")
    private String paramDescribe;
    @TableField(exist = false)
    private List<ParamDetail> ParamDetails;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getParamDescribe() {
        return paramDescribe;
    }

    public void setParamDescribe(String paramDescribe) {
        this.paramDescribe = paramDescribe;
    }

    public List<ParamDetail> getParamDetails() {
        return ParamDetails;
    }

    public void setParamDetails(List<ParamDetail> paramDetails) {
        ParamDetails = paramDetails;
    }

    @Override
    public String toString() {
        return "Param{" +
                "id=" + id +
                ", paramName='" + paramName + '\'' +
                ", paramDescribe='" + paramDescribe + '\'' +
                ", ParamDetails=" + ParamDetails +
                '}';
    }
}
