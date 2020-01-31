package com.impetrostock.im.Model;

public class Tax_Data
{
    String Title;
    String TaxRate;
    String Type;

    public Tax_Data(String title, String taxRate, String type) {
        Title = title;
        TaxRate = taxRate;
        Type = type;
    }


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(String taxRate) {
        TaxRate = taxRate;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }
}
