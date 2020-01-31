package com.impetrostock.im.Model;

public class Product_Data
{
    String Name;
    String id;
    String Price;
    String Cost;
    int Quantity;
    String Category;

     public Product_Data(String name, String id, String price, String cost, int quantity, String category) {
         this.Name = name;
         this.id = id;
         this.Price = price;
         this.Cost = cost;
         this.Quantity = quantity;
         this.Category = category;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int quantity) {
        Quantity = quantity;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
