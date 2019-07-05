package com.example.halaljava.database;

import java.util.Date;
import java.util.Locale;

public class Expense {
    private String itemTitle;
    private String category;
    private double itemAmount;
    private byte itemType;
    private Date date;

    public Expense() {
    }

    public Expense(String n, double a, String cat, byte type) {
        itemTitle = n;
        itemAmount = a;
        category = cat;
        itemType = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String cat) {
        category = cat;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String name) {
        itemTitle = name;
    }

    public String getItemType() {
        String s = "";
        if(itemType == 0)
            s = "-";
        else if (itemType == 1)
            s = "+";
        return s;
    }

    public void setItemType(byte type) {
        itemType = type;
    }

    public String getItemAmount() {
        String s = String.format(Locale.ENGLISH,"%.2f", itemAmount);
        return s;
    }

    public void setItemAmount(double itemAm) {
        itemAmount = itemAm;
    }

    public String getDate() {
        return date.toString();
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
