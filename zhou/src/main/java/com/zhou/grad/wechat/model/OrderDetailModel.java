package com.zhou.grad.wechat.model;

import java.util.Date;
import java.util.List;

public class OrderDetailModel {

    private Long orderId;

    private String orderCode;

    private Date orderTime;

    private String openId;

    private String nickName;

    private Double sumMoney;

    private String status;

    private String remark;
    
    private List<CartItemModel> foods;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Double getSumMoney() {
        return sumMoney;
    }

    public void setSumMoney(Double sumMoney) {
        this.sumMoney = sumMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<CartItemModel> getFoods() {
        return foods;
    }

    public void setFoods(List<CartItemModel> foods) {
        this.foods = foods;
    }

    @Override
    public String toString() {
        return "OrderDetailModel [orderId=" + orderId + ", orderCode=" + orderCode + ", orderTime=" + orderTime
                + ", openId=" + openId + ", nickName=" + nickName + ", sumMoney=" + sumMoney + ", status=" + status
                + ", remark=" + remark + ", foods=" + foods + "]";
    }
}
