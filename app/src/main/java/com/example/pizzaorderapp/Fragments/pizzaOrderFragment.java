package com.example.pizzaorderapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pizzaorderapp.MainActivity;
import com.example.pizzaorderapp.databinding.FragmentPizzaOrderBinding;

public class pizzaOrderFragment extends Fragment {

    FragmentPizzaOrderBinding binding;
    MainActivity activity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentPizzaOrderBinding.inflate(getLayoutInflater(),container,false);
        activity = (MainActivity) getActivity();

        return binding.getRoot();
    }
}