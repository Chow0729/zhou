package com.zhou.grad.wechat.model;

public class CartItemModel {

    private String name;
    
    private Double price;
    
    private String detail;
    
    private int number;
    
    private Double sum;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    @Override
    public String toString() {
        return "CartItemModel [name=" + name + ", price=" + price + ", detail=" + detail + ", number=" + number
                + ", sum=" + sum + "]";
    }
    
}
