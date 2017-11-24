package com.cz.enums;

/**
 * Created by jomalone_jia on 2017/9/15.
 */
public enum OrderStatus {

    CANCELED(0),
    COMPLETED(1),
    AWAITING_EXCHANGE(2),
    AWAITING_SHIPPING(3),
    SHIPPING(4),
    DELIVERED(5),
    AWAITING_RETURNING(6),
    RETURNING(7),
    RETURNED(8);

    private final int status;

    OrderStatus(int status){
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

}
