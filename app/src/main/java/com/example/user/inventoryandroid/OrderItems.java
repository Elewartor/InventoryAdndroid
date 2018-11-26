package com.example.user.inventoryandroid;

public class OrderItems {

    private String name;
    private String author;
    private String year;
    private String date;
    private String days;
    private String endDate;

    public OrderItems(String name, String author, String year, String date, String days, String endDate) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.date = date;
        this.days = days;
        this.endDate = endDate;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getYear() {
        return year;
    }

    public String getDate() {
        return date;
    }

    public String getDays() {
        return days;
    }

    public String getEndDate() {
        return endDate;
    }
}
