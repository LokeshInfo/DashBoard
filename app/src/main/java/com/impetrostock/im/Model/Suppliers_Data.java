package com.impetrostock.im.Model;

public class Suppliers_Data
{
    String Name;
    String Company;
    String Phone;
    String Email;
    String City;
    String Country;

    public Suppliers_Data(String name, String company, String phone, String email, String city, String country) {
        Name = name;
        Company = company;
        Phone = phone;
        Email = email;
        City = city;
        Country = country;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCompany() {
        return Company;
    }

    public void setCompany(String company) {
        Company = company;
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

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }
}
