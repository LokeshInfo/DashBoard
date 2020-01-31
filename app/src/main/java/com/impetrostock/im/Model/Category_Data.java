package com.impetrostock.im.Model;

public class Category_Data
{
    String CName;
    String CCode;

    public Category_Data(String CName, String CCode) {
        this.CName = CName;
        this.CCode = CCode;
    }

    public String getCName() {
        return CName;
    }

    public void setCName(String CName) {
        this.CName = CName;
    }

    public String getCCode() {
        return CCode;
    }

    public void setCCode(String CCode) {
        this.CCode = CCode;
    }
}
