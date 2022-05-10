package com.example.pizzaorderapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class BottomFragmentsAdapter extends FragmentStateAdapter {


    public BottomFragmentsAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new pizzaOrderFragment();

            case 1:
                return new pizzaCartFragment();
        }
        return new pizzaOrderFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
