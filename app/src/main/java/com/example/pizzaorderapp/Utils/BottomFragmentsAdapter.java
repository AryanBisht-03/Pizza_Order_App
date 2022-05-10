package com.example.pizzaorderapp.Utils;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.pizzaorderapp.Fragments.pizzaCartFragment;
import com.example.pizzaorderapp.Fragments.pizzaOrderFragment;

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
                return new pizzaCartFragment();

            case 1:
                return new pizzaOrderFragment();
        }
        return new pizzaOrderFragment();
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
