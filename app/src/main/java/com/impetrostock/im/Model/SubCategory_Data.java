package com.impetrostock.im.Model;

public class SubCategory_Data
{
    String subCategoryCode;
    String SubCategoryName;
    String MainCategory;

    public SubCategory_Data(String subCategoryCode, String subCategoryName, String mainCategory) {
        this.subCategoryCode = subCategoryCode;
        SubCategoryName = subCategoryName;
        MainCategory = mainCategory;
    }

    public String getSubCategoryCode() {
        return subCategoryCode;
    }

    public void setSubCategoryCode(String subCategoryCode) {
        this.subCategoryCode = subCategoryCode;
    }

    public String getSubCategoryName() {
        return SubCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        SubCategoryName = subCategoryName;
    }

    public String getMainCategory() {
        return MainCategory;
    }

    public void setMainCategory(String mainCategory) {
        MainCategory = mainCategory;
    }
}
