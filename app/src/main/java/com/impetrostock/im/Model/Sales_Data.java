package com.impetrostock.im.Model;

public class Sales_Data
{
    String date;
    String refrencenum;
    String Biller;
    String Customer;
    String ProductTax;
    String InvoiceTax;
    String Total;
    String PaidAmt;
    String DueAmt;

    public Sales_Data(String date, String refrencenum, String biller, String customer, String productTax, String invoiceTax, String total, String paidAmt, String dueAmt) {
        this.date = date;
        this.refrencenum = refrencenum;
        Biller = biller;
        Customer = customer;
        ProductTax = productTax;
        InvoiceTax = invoiceTax;
        Total = total;
        PaidAmt = paidAmt;
        DueAmt = dueAmt;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRefrencenum() {
        return refrencenum;
    }

    public void setRefrencenum(String refrencenum) {
        this.refrencenum = refrencenum;
    }

    public String getBiller() {
        return Biller;
    }

    public void setBiller(String biller) {
        Biller = biller;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public String getProductTax() {
        return ProductTax;
    }

    public void setProductTax(String productTax) {
        ProductTax = productTax;
    }

    public String getInvoiceTax() {
        return InvoiceTax;
    }

    public void setInvoiceTax(String invoiceTax) {
        InvoiceTax = invoiceTax;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getPaidAmt() {
        return PaidAmt;
    }

    public void setPaidAmt(String paidAmt) {
        PaidAmt = paidAmt;
    }

    public String getDueAmt() {
        return DueAmt;
    }

    public void setDueAmt(String dueAmt) {
        DueAmt = dueAmt;
    }
}
