package com.example.pizzaorderapp.Utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.pizzaorderapp.Fragments.pizzaCartFragment;
import com.example.pizzaorderapp.Fragments.pizzaOrderFragment;

public class BottomFragmentsAdapter extends FragmentStateAdapter {

    pizzaCartFragment cartFragment;
    pizzaOrderFragment orderFragment;

    public pizzaOrderFragment getOrderFragment() {
        return orderFragment;
    }

    public BottomFragmentsAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
        cartFragment = new pizzaCartFragment();
        orderFragment = new pizzaOrderFragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return cartFragment;

            case 1:
                return orderFragment;
        }
        return cartFragment;
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
