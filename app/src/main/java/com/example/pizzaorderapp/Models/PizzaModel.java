package com.example.pizzaorderapp.Models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class PizzaModel {
    String name,id,description;
    ArrayList<CrustModel> crusts = new ArrayList<>();
    Boolean isVeg;
    Integer defaultCrust;
    public PizzaModel(JSONObject object) {
        super();
        try{
            name = object.getString("name");
            isVeg = object.getBoolean("isVeg");
            description = object.getString("description");
            defaultCrust = object.getInt("defaultCrust");
            id = object.getString("id");
            JSONArray array = object.getJSONArray("crusts");
            for(Integer i=0;i<array.length();i++)
            {
                JSONObject value = array.getJSONObject(i);
                CrustModel model = new CrustModel();
                model.setCrustModel(value);
                crusts.add(model);
            }
        }catch (Exception e)
        {
            Log.d("Aryan","Error is in json :- "+e.toString());
        }
    }

    public PizzaModel(){}
    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<CrustModel> getCrusts() {
        return crusts;
    }

    public Boolean getVeg() {
        return isVeg;
    }

    public Integer getDefaultCrust() {
        return defaultCrust;
    }
}
