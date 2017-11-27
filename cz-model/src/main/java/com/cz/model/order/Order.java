package com.cz.model.order;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.cz.enums.OrderStatus;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by jomalone_jia on 2017/9/15.
 */
@TableName("sys_order")
public class Order implements Serializable {

    private static final long serialVersionUID = -4897636024388431942L;

    @TableId
    private String id;
    @TableField("order_number")
    private String orderNumber;
    private String username;
    private String image;
    @TableField("item_id")
    private String itemId;
    @TableField("item_name")
    private String itemName;
    @TableField(exist = false)
    private String address;
    private OrderStatus status;
    @TableField("post_fee")
    private Float postFee;
    private Float price;
    private Float discount;
    private Integer count;
    @TableField("total_price")
    private Float totalPrice;
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;
    @TableField(value = "payment_time")
    private Date paymentTime;
    @TableField(value = "consign_time")
    private Date consignTime;
    @TableField(value = "end_time")
    private Date endTime;
    @TableField(value = "close_time")
    private Date closeTime;
    @TableField("shipping_name")
    private String shippingName;
    @TableField("shipping_number")
    private String shippingNumber;
    @TableField("order_message")
    private String orderMessage;
    @TableField(exist = false)
    private List<OrderTrack> orderTracks;

    public Order(){}

    public Order(String id, String orderNumber, String username, String image, String itemId, String itemName, String address, OrderStatus status, Float postFee, Float price, Float discount, Integer count, Float totalPrice, Date createTime, Date updateTime, Date paymentTime, Date consignTime, Date endTime, Date closeTime, String shippingName, String shippingNumber, String orderMessage, List<OrderTrack> orderTracks) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.username = username;
        this.image = image;
        this.itemId = itemId;
        this.itemName = itemName;
        this.address = address;
        this.status = status;
        this.postFee = postFee;
        this.price = price;
        this.discount = discount;
        this.count = count;
        this.totalPrice = totalPrice;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.paymentTime = paymentTime;
        this.consignTime = consignTime;
        this.endTime = endTime;
        this.closeTime = closeTime;
        this.shippingName = shippingName;
        this.shippingNumber = shippingNumber;
        this.orderMessage = orderMessage;
        this.orderTracks = orderTracks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Float getPostFee() {
        return postFee;
    }

    public void setPostFee(Float postFee) {
        this.postFee = postFee;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(Date paymentTime) {
        this.paymentTime = paymentTime;
    }

    public Date getConsignTime() {
        return consignTime;
    }

    public void setConsignTime(Date consignTime) {
        this.consignTime = consignTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public String getShippingName() {
        return shippingName;
    }

    public void setShippingName(String shippingName) {
        this.shippingName = shippingName;
    }

    public String getShippingNumber() {
        return shippingNumber;
    }

    public void setShippingNumber(String shippingNumber) {
        this.shippingNumber = shippingNumber;
    }

    public String getOrderMessage() {
        return orderMessage;
    }

    public void setOrderMessage(String orderMessage) {
        this.orderMessage = orderMessage;
    }

    public List<OrderTrack> getOrderTracks() {
        return orderTracks;
    }

    public void setOrderTracks(List<OrderTrack> orderTracks) {
        this.orderTracks = orderTracks;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", username='" + username + '\'' +
                ", image='" + image + '\'' +
                ", itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", address='" + address + '\'' +
                ", status=" + status +
                ", postFee=" + postFee +
                ", price=" + price +
                ", discount=" + discount +
                ", count=" + count +
                ", totalPrice=" + totalPrice +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", paymentTime=" + paymentTime +
                ", consignTime=" + consignTime +
                ", endTime=" + endTime +
                ", closeTime=" + closeTime +
                ", shippingName='" + shippingName + '\'' +
                ", shippingNumber='" + shippingNumber + '\'' +
                ", orderMessage='" + orderMessage + '\'' +
                ", orderTracks=" + orderTracks +
                '}';
    }
}
