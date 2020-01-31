package com.impetrostock.im.Model;

public class Purchases_Data
{
    String date;
    String refrenceNum;
    String Supplier;
    String Pay;

    public Purchases_Data(String date, String refrenceNum, String supplier, String pay) {
        this.date = date;
        this.refrenceNum = refrenceNum;
        Supplier = supplier;
        Pay = pay;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRefrenceNum() {
        return refrenceNum;
    }

    public void setRefrenceNum(String refrenceNum) {
        this.refrenceNum = refrenceNum;
    }

    public String getSupplier() {
        return Supplier;
    }

    public void setSupplier(String supplier) {
        Supplier = supplier;
    }

    public String getPay() {
        return Pay;
    }

    public void setPay(String pay) {
        Pay = pay;
    }
}
