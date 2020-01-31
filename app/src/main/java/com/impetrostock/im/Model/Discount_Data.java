package com.impetrostock.im.Model;

public class Discount_Data
{
    String Title;
    String Discount;
    String Type;

    public Discount_Data(String title, String discount, String type) {
        Title = title;
        Discount = discount;
        Type = type;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
