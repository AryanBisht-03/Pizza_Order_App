package com.example.pizzaorderapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.util.Pair;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pizzaorderapp.Interface.PriceControlInterface;
import com.example.pizzaorderapp.Interface.addtoCardInterface;
import com.example.pizzaorderapp.Models.OrderedPizza;
import com.example.pizzaorderapp.RecyclerViews.orderListRecyclerView;
import com.example.pizzaorderapp.RecyclerViews.pizzaListRecyclerView;
import com.example.pizzaorderapp.Utils.BottomFragmentsAdapter;
import com.example.pizzaorderapp.Models.PizzaModel;
import com.example.pizzaorderapp.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationBarView;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements addtoCardInterface, PriceControlInterface {

    ActivityMainBinding binding;
    RequestQueue queue;
    ArrayList<PizzaModel> pizzas;
    public ArrayList<OrderedPizza> orders;
    public orderListRecyclerView orderadatper;
    public pizzaListRecyclerView pizzaadapter;
    BottomFragmentsAdapter navigationAdapter;
    public HashMap<String, Pair> orderList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        navigationAdapter = new BottomFragmentsAdapter(this);

        orderList = new HashMap<>();
        binding.viewPager.setAdapter(navigationAdapter);
        pizzas = new ArrayList<>();
        orders = new ArrayList<>();
        orderadatper = new orderListRecyclerView(this,orders,this);
        pizzaadapter = new pizzaListRecyclerView(this,pizzas, this);

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
        HttpsTrustManager.allowAllSSL();
        JsonObjectRequest userReg = new JsonObjectRequest(Request.Method.GET, userUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                            PizzaModel pizza = new PizzaModel(response);
                            pizzas.add(pizza);
                            pizzaadapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Aryan","Error is : - " + error.getMessage());
            }
        });

        queue.add(userReg);
    }

    @Override
    public void addtoCartItem(PizzaModel model) {
        binding.bottomNavigationView.setVisibility(View.GONE);
        binding.cardView.setVisibility(View.VISIBLE);
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        ArrayList<String> crustValue = new ArrayList<>();
        for(Integer i=0;i<model.getCrusts().size();i++)
        {
            crustValue.add(model.getCrusts().get(i).getCrustName());
            map.put(model.getCrusts().get(i).getCrustName(),i);
        }
        ArrayAdapter<String> crustAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,crustValue);
        binding.crustSpinner.setAdapter(crustAdapter);
        binding.crustSpinner.setSelection(model.getDefaultCrust()-1);
        binding.crustSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String crust = adapterView.getSelectedItem().toString();

                Integer idx = map.get(crust);
                ArrayList<String> sizeValue = new ArrayList<>();
                for(i=0;i<model.getCrusts().get(idx).getSizes().size();i++)
                {
                    sizeValue.add(model.getCrusts().get(idx).getSizes().get(i).getSizeName());
                }
                ArrayAdapter<String> sizeAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_spinner_dropdown_item,sizeValue);
                binding.sizespinner.setAdapter(sizeAdapter);

                binding.sizespinner.setSelection(model.getCrusts().get(idx).getDefaultSize()-1);
                binding.sizespinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        Integer money = model.getCrusts().get(idx).getSizes().get(i).getPrice();
                        binding.rupeeText.setText(money.toString());
                        binding.confirmButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                OrderedPizza orderedPizza = new OrderedPizza();
                                orderedPizza.setCrusts(crust);
                                orderedPizza.setDescription(model.getDescription());
                                orderedPizza.setName(model.getName());
                                orderedPizza.setSize(model.getCrusts().get(idx).getSizes().get(i).getSizeName());
                                orderedPizza.setPrice(money);
                                orderedPizza.setVeg(model.getVeg());
                                String key = model.getName()+crust+model.getCrusts().get(idx).getSizes().get(i).getSizeName();
                                if(!orderList.containsKey(key))
                                {
                                    Pair p1 = new Pair(1,money);
                                    orderList.put(key,p1);
                                    orders.add(orderedPizza);
                                }
                                else {
                                    Pair p1 = new Pair((Integer)orderList.get(key).first + 1,money);
                                    orderList.put(key, p1);
                                }
                                orderedPizza.setCount(orderedPizza.getCount()+1);
                                orderadatper.notifyDataSetChanged();
                                binding.bottomNavigationView.setVisibility(View.VISIBLE);
                                binding.cardView.setVisibility(View.GONE);
                                Toast.makeText(MainActivity.this, "Item is added to Cart", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        binding.cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.bottomNavigationView.setVisibility(View.VISIBLE);
                binding.cardView.setVisibility(View.GONE);
            }
        });

        binding.confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    public void addOrSubtraceItem(OrderedPizza model,boolean add) {
        Log.d("Aryan","Inside add or substrace");
        String key = model.getName()+model.getCrusts()+model.getSize();
        int val = add? 1:-1;
        if(!add && (Integer) orderList.get(key).first <= 1)
            return;

        Pair p1 = new Pair((Integer)orderList.get(key).first + val,orderList.get(key).second);
        model.setCount(model.getCount()+val);
        orderadatper.notifyDataSetChanged();
        orderList.put(key,p1);
        navigationAdapter.getOrderFragment().onResume();
    }
}