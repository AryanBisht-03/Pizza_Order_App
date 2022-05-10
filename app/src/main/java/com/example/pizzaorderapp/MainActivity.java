package com.example.pizzaorderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pizzaorderapp.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        BottomFragmentsAdapter navigationAdapter = new BottomFragmentsAdapter(this);

        binding.viewPager.setAdapter(navigationAdapter);

        binding.bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.pizza:
                        binding.viewPager.setCurrentItem(0);
                        break;
                    case R.id.cart:
                        binding.viewPager.setCurrentItem(1);
                        break;
                }
                return true;
            }
        });


        binding.viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                switch (position) {
                    case 0:
                        binding.bottomNavigationView.getMenu().findItem(R.id.pizza).setChecked(true);
                        break;
                    case 1:
                        binding.bottomNavigationView.getMenu().findItem(R.id.cart).setChecked(true);
                        break;
                }
            }
        });
        String userUrl = "https://625bbd9d50128c570206e502.mockapi.io/api/v1/pizza/1";
        queue = Volley.newRequestQueue(this);

        JsonObjectRequest userReg = new JsonObjectRequest(Request.Method.GET, userUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("Aryan","response: - " + response.toString());
                            String name = response.getString("name");
                            Log.d("Aryan","Name:- "+name);
//                            user = new MainUser(response.getInt("station_code"),response.getString("name"),
//                                    response.getString("url"));
//                            Log.d("Aryan",user.name+" "+user.station_code);
                        } catch (JSONException e) {
                            Log.d("Aryan","Some exception occurs");
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Aryan","Error is : - " + error.getMessage());
            }
        });

        queue.add(userReg);
    }
}