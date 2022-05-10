package com.example.pizzaorderapp.Models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CrustModel {
    String crustName;
    Integer defaultSize,crustID;
    ArrayList<SizeModel> sizes = new ArrayList<>();
    public void setCrustModel(JSONObject object)
    {
        try {
            crustID = object.getInt("id");
            defaultSize = object.getInt("defaultSize");
            crustName = object.getString("name");
            JSONArray array = object.getJSONArray("sizes");
            for(Integer i=0;i<array.length();i++)
            {
                JSONObject value = array.getJSONObject(i);
                SizeModel model = new SizeModel();
                model.setSizeName(value);
                sizes.add(model);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public CrustModel(){}

    public String getCrustName() {
        return crustName;
    }

    public Integer getDefaultSize() {
        return defaultSize;
    }

    public Integer getCrustID() {
        return crustID;
    }

    public ArrayList<SizeModel> getSizes() {
        return sizes;
    }
}