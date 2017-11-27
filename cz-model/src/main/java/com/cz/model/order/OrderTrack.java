package com.cz.model.order;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by jomalone_jia on 2017/11/27.
 */
@TableName("sys_order_track")
public class OrderTrack implements Serializable {

    private static final long serialVersionUID = -5525945043167886294L;

    @TableId
    private String id;
    @TableField("order_id")
    private String orderId;
    @TableField("track_information")
    private String trackInformation;
    @TableField(value = "track_time",fill = FieldFill.INSERT_UPDATE)
    private Date trackTime;

    public OrderTrack(){}

    public OrderTrack(String orderId, String trackInformation) {
        this.orderId = orderId;
        this.trackInformation = trackInformation;
    }


    public Date getTrackTime() {
        return trackTime;
    }

    public void setTrackTime(Date trackTime) {
        this.trackTime = trackTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTrackInformation() {
        return trackInformation;
    }

    public void setTrackInformation(String trackInformation) {
        this.trackInformation = trackInformation;
    }

    @Override
    public String toString() {
        return "OrderTrack{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", trackInformation='" + trackInformation + '\'' +
                ", trackTime=" + trackTime +
                '}';
    }
}
