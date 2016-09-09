package com.example.ankur.currencyconverter;

/**
 * Created by ankur on 9/9/16.
 */
public class RecordDetail {
    String date, currency;
    int id, quantity;
    double buyValue;

    public RecordDetail(String date, String currency, int id, int quantity, double buyValue) {
        this.date = date;
        this.currency = currency;
        this.id = id;
        this.quantity = quantity;
        this.buyValue = buyValue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getBuyValue() {
        return buyValue;
    }

    public void setBuyValue(double buyValue) {
        this.buyValue = buyValue;
    }
}
