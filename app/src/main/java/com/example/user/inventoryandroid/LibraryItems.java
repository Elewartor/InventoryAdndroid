package com.example.user.inventoryandroid;

public class LibraryItems {

   private String name, author, year, gCount, left;

    public LibraryItems(String name, String author, String year, String gCount, String left) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.gCount = gCount;
        this.left = left;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getgCount() {
        return gCount;
    }

    public void setgCount(String gCount) {
        this.gCount = gCount;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }
}
