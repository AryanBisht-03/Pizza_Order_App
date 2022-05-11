package com.example.pizzaorderapp.RecyclerViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzaorderapp.Interface.PriceControlInterface;
import com.example.pizzaorderapp.Models.OrderedPizza;
import com.example.pizzaorderapp.R;
import com.example.pizzaorderapp.Models.PizzaModel;
import com.example.pizzaorderapp.databinding.OrderlistRecyclerBinding;

import java.util.ArrayList;

public class orderListRecyclerView extends RecyclerView.Adapter<orderListRecyclerView.orderListViewHolder> {

    Context context;
    ArrayList<OrderedPizza> items;
    PriceControlInterface reporterListener;
    public orderListRecyclerView(Context context, ArrayList<OrderedPizza> items, PriceControlInterface reporterListener) {
        this.context = context;
        this.items = items;
        this.reporterListener = reporterListener;
    }

    @NonNull
    @Override
    public orderListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.orderlist_recycler,parent,false);
        return new orderListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull orderListViewHolder holder, int position) {
        OrderedPizza model = items.get(position);
        holder.binding.pizzaName.setText(model.getName());
        holder.binding.despcription.setText(model.getDescription());
        if(model.getVeg())
            holder.binding.vegImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_cricle_green));
        else
            holder.binding.vegImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_cricle_red));

        holder.binding.crust.setText(model.getCrusts());
        holder.binding.sizeText.setText(model.getSize());
        holder.binding.priceText.setText("Rs. "+model.getPrice().toString());

        holder.binding.countofDish.setText(model.getCount().toString());
        holder.binding.plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reporterListener.addOrSubtraceItem(model,true);
            }
        });

        holder.binding.minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reporterListener.addOrSubtraceItem(model,false);
            }
        });
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