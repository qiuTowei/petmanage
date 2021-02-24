package com.neo.entity;

import java.util.Date;

public class Bill {
    private String billID;
    private String billTime;
    private String freshID;
    private String userID;
    private String billState;
    private String freshName;
    private String freshSize;
    private String receiver;
    private String phone;
    private String address;
    private String userName;
    private int freshPrice;
    private String type;
    private String logisticsID;
    private String company;
    private String logistics;


    public String getFreshID() {
        return freshID;
    }

    public void setFreshID(String freshID) {
        this.freshID = freshID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getBillTime() {
        return billTime;
    }

    public void setBillTime(String billTime) {
        this.billTime = billTime;
    }

    public String getBillState() {
        return billState;
    }

    public void setBillState(String billState) {
        this.billState = billState;
    }

    public String getFreshName() {
        return freshName;
    }

    public void setFreshName(String freshName) {
        this.freshName = freshName;
    }

    public String getFreshSize() {
        return freshSize;
    }

    public void setFreshSize(String freshSize) {
        this.freshSize = freshSize;
    }

    public int getFreshPrice() {
        return freshPrice;
    }

    public void setFreshPrice(int freshPrice) {
        this.freshPrice = freshPrice;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLogisticsID() {
        return logisticsID;
    }

    public void setLogisticsID(String logisticsID) {
        this.logisticsID = logisticsID;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getLogistics() {
        return logistics;
    }

    public void setLogistics(String logistics) {
        this.logistics = logistics;
    }
}
