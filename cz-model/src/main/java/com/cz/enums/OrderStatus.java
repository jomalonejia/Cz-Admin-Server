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
    DELIVERING(5),
    DELIVERED(6),
    AWAITING_COMMENT(7),
    AWAITING_RETURNING(11),
    RETURNING(12),
    RETURNED(13);

    private int status;
    private static OrderStatus[] statuses = values();

    OrderStatus(int status){
        this.status = status;
    }

    public int getStatus() {
        return status;
    }

    public OrderStatus next(){
        if(7 == this.ordinal()){
            return statuses[1];
        }else if(0 == this.ordinal() || 1 == this.ordinal() || 13 == this.ordinal()){
            return statuses[this.ordinal()];
        }else {
            return statuses[(this.ordinal() + 1) % statuses.length];
        }
    }


    public OrderStatus cancel(){
        return statuses[0];
    }

    public OrderStatus prepareReturning(){
        return statuses[11];
    }
}
