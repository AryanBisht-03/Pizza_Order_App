package com.example.pizzaorderapp.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizzaorderapp.MainActivity;
import com.example.pizzaorderapp.Models.OrderedPizza;
import com.example.pizzaorderapp.databinding.FragmentPizzaOrderBinding;

import java.util.ArrayList;
import java.util.Map;

public class pizzaOrderFragment extends Fragment {

    FragmentPizzaOrderBinding binding;
    MainActivity activity;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPizzaOrderBinding.inflate(getLayoutInflater(),container,false);
        activity = (MainActivity) getActivity();
        binding.recyclervieworder.setAdapter(activity.orderadatper);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        Log.d("Aryan","Inside fragment resume ");
        super.onResume();
        Integer cost = 0;
        for (Map.Entry<String, Pair> entry : activity.orderList.entrySet()) {
            Pair value = entry.getValue();
            cost =  cost + (Integer) value.first* (Integer) value.second;
        }
        binding.moneyText.setText(cost.toString());
    }
}