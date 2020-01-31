package com.impetrostock.im.Model;

public class Warehouse_Data
{
    String WCode;
    String WName;
    String WAddress;
    String WCity;

    public Warehouse_Data(String WCode, String WName, String WAddress, String WCity) {
        this.WCode = WCode;
        this.WName = WName;
        this.WAddress = WAddress;
        this.WCity = WCity;
    }

    public String getWCode() {
        return WCode;
    }

    public void setWCode(String WCode) {
        this.WCode = WCode;
    }

    public String getWName() {
        return WName;
    }

    public void setWName(String WName) {
        this.WName = WName;
    }

    public String getWAddress() {
        return WAddress;
    }

    public void setWAddress(String WAddress) {
        this.WAddress = WAddress;
    }

    public String getWCity() {
        return WCity;
    }

    public void setWCity(String WCity) {
        this.WCity = WCity;
    }
}
