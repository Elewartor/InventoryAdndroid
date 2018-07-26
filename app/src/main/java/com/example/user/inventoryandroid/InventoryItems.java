package com.example.user.inventoryandroid;

public class InventoryItems {

    private String id;
    private String name;
    private String description;
    private String status;
    private String res_person;
    private String price;
    private String buy_date;
    private String dep_time;
    private String location;
    private String category;
    private String image;
    private String comment;

    public InventoryItems(String id, String name, String description, String status,
                          String res_person, String price, String buy_date, String dep_time,
                          String location, String category, String image, String comment) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
        this.res_person = res_person;
        this.price = price;
        this.buy_date = buy_date;
        this.dep_time = dep_time;
        this.location = location;
        this.category = category;
        this.image = image;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getRes_person() {
        return res_person;
    }

    public void setRes_person(String res_person) {
        this.res_person = res_person;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBuy_date() {
        return buy_date;
    }

    public void setBuy_date(String buy_date) {
        this.buy_date = buy_date;
    }

    public String getDep_time() {
        return dep_time;
    }

    public void setDep_time(String dep_time) {
        this.dep_time = dep_time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
