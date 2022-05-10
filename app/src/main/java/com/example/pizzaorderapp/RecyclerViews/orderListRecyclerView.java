package com.example.pizzaorderapp.RecyclerViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzaorderapp.R;
import com.example.pizzaorderapp.Models.PizzaModel;
import com.example.pizzaorderapp.databinding.OrderlistRecyclerBinding;

import java.util.ArrayList;

public class orderListRecyclerView extends RecyclerView.Adapter<orderListRecyclerView.orderListViewHolder> {

    Context context;
    ArrayList<PizzaModel> items;

    public orderListRecyclerView(Context context, ArrayList<PizzaModel> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public orderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.orderlist_recycler,parent,false);
        return new orderListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull orderListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class orderListViewHolder extends RecyclerView.ViewHolder{

        OrderlistRecyclerBinding binding;
        public orderListViewHolder(@NonNull View itemView) {
            super(itemView);

            binding = OrderlistRecyclerBinding.bind(itemView);
        }
    }
}