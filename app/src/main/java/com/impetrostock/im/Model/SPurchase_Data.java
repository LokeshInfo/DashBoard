package com.impetrostock.im.Model;

public class SPurchase_Data
{
    String Date;
    String RefrenceNum;
    String supplier;
    String Total;

    public SPurchase_Data(String date, String refrenceNum, String supplier, String total) {
        Date = date;
        RefrenceNum = refrenceNum;
        this.supplier = supplier;
        Total = total;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getRefrenceNum() {
        return RefrenceNum;
    }

    public void setRefrenceNum(String refrenceNum) {
        RefrenceNum = refrenceNum;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }
}
