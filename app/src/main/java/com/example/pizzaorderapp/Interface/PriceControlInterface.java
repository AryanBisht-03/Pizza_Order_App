package com.example.pizzaorderapp.Interface;

import com.example.pizzaorderapp.Models.OrderedPizza;

public interface PriceControlInterface {
    void addOrSubtraceItem(OrderedPizza model,boolean add);
}
