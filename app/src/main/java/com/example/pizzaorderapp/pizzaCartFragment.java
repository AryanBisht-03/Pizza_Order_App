package com.example.pizzaorderapp;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizzaorderapp.databinding.FragmentPizzaCartBinding;


public class pizzaCartFragment extends Fragment {

    MainActivity activity;
    FragmentPizzaCartBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPizzaCartBinding.inflate(getLayoutInflater(), container, false);
        activity = (MainActivity) getActivity();

        return binding.getRoot();
    }
}