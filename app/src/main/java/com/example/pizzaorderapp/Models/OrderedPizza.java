package com.example.pizzaorderapp.Models;

import android.util.Size;

import java.util.ArrayList;

public class OrderedPizza {
    String name,id,description,crusts,size;
    Boolean isVeg;
    Integer price,count;

    public OrderedPizza(){
        count=0;
    }
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCrusts() {
        return crusts;
    }

    public void setCrusts(String crusts) {
        this.crusts = crusts;
    }

    public Boolean getVeg() {
        return isVeg;
    }

    public void setVeg(Boolean veg) {
        isVeg = veg;
    }

}
