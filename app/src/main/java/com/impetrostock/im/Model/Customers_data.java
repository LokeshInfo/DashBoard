package com.impetrostock.im.Model;

public class Customers_data
{
    String Name;
    String Phone;
    String Email;
    String Address;

    public Customers_data(String name, String phone, String email, String address) {
        Name = name;
        Phone = phone;
        Email = email;
        Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
