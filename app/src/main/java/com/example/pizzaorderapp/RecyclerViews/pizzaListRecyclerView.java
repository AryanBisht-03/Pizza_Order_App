package com.example.pizzaorderapp.RecyclerViews;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pizzaorderapp.Interface.addtoCardInterface;
import com.example.pizzaorderapp.Models.CrustModel;
import com.example.pizzaorderapp.Models.SizeModel;
import com.example.pizzaorderapp.R;
import com.example.pizzaorderapp.Models.PizzaModel;
import com.example.pizzaorderapp.databinding.PizzalistRecyclerBinding;

import java.util.ArrayList;

public class pizzaListRecyclerView extends RecyclerView.Adapter<pizzaListRecyclerView.pizzaListViewHolder>  {

    Context context;
    ArrayList<PizzaModel> items;
    addtoCardInterface reportlistener;
    public pizzaListRecyclerView(Context context, ArrayList<PizzaModel> items, addtoCardInterface reportlistener) {
        this.context = context;
        this.items = items;
        this.reportlistener = reportlistener;
    }

    @NonNull
    @Override
    public pizzaListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pizzalist_recycler,parent,false);
        return new pizzaListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull pizzaListViewHolder holder, int position) {
        PizzaModel model = items.get(position);
        holder.binding.pizzaName.setText(model.getName());
        holder.binding.despcription.setText(model.getDescription());
        if(model.getVeg())
            holder.binding.vegImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_cricle_green));
        else
            holder.binding.vegImage.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_cricle_red));

        holder.binding.crustName.setText(model.getCrusts().get(0).getCrustName());
        holder.binding.addCartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reportlistener.addtoCartItem(model);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class pizzaListViewHolder extends RecyclerView.ViewHolder{

        PizzalistRecyclerBinding binding;
        public pizzaListViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = PizzalistRecyclerBinding.bind(itemView);
        }
    }
}
