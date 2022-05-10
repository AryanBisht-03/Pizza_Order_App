package com.example.pizzaorderapp.Models;

import org.json.JSONException;
import org.json.JSONObject;

public class SizeModel{
    String sizeName;
    Integer price,sizeID;

    void setSizeName(JSONObject object)
    {
        try {
            sizeName = object.getString("name");
            sizeID = object.getInt("id");
            price = object.getInt("price");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getSizeName() {
        return sizeName;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getSizeID() {
        return sizeID;
    }
}