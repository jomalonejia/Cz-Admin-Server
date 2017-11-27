package com.cz.dto.order;

import java.io.Serializable;

/**
 * Created by jomalone_jia on 2017/11/27.
 */
public class OrderTrackDto implements Serializable {

    private static final long serialVersionUID = -9042578404315380575L;

    private String orderId;
    private String trackInformation;

    @Override
    public String toString() {
        return "OrderTrackDto{" +
                "orderId='" + orderId + '\'' +
                ", trackInformation='" + trackInformation + '\'' +
                '}';
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
}
