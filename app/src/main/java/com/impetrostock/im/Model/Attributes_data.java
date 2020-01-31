package com.impetrostock.im.Model;

public class Attributes_data
{
    String CatName;
    String SubCatName;
    String Fields;

    public Attributes_data(String catName, String subCatName, String fields) {
        CatName = catName;
        SubCatName = subCatName;
        Fields = fields;
    }

    public String getCatName() {
        return CatName;
    }

    public void setCatName(String catName) {
        CatName = catName;
    }

    public String getSubCatName() {
        return SubCatName;
    }

    public void setSubCatName(String subCatName) {
        SubCatName = subCatName;
    }

    public String getFields() {
        return Fields;
    }

    public void setFields(String fields) {
        Fields = fields;
    }
}
