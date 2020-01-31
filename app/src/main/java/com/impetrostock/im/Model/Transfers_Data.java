package com.impetrostock.im.Model;

public class Transfers_Data
{
    String Date;
    String Tranfer_Num;
    String WFrom;
    String Wto;

    public Transfers_Data(String date, String tranfer_Num, String WFrom, String wto) {
        Date = date;
        Tranfer_Num = tranfer_Num;
        this.WFrom = WFrom;
        Wto = wto;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTranfer_Num() {
        return Tranfer_Num;
    }

    public void setTranfer_Num(String tranfer_Num) {
        Tranfer_Num = tranfer_Num;
    }

    public String getWFrom() {
        return WFrom;
    }

    public void setWFrom(String WFrom) {
        this.WFrom = WFrom;
    }

    public String getWto() {
        return Wto;
    }

    public void setWto(String wto) {
        Wto = wto;
    }
}
