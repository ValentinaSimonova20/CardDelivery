package com.startandroid.trashstatf;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    ArrayList<Order> orders;

    public MyAdapter(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        holder.cardType.setText(orders.get(position).getCardType());
        holder.cardName.setText(orders.get(position).getCardName());
        holder.orderStatus.setText(orders.get(position).getStatus());
        String address = orders.get(position).getCity()+", "+orders.get(position).getStreet()+", "+
                orders.get(position).getHome()+" k "+orders.get(position).getKorp()+", entrance: "+
                orders.get(position).getEntrance()+", flat "+orders.get(position).getFlat();
        holder.clientAddress.setText(address);

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView cardType, cardName, clientAddress, orderStatus;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            cardType = itemView.findViewById(R.id.displayCardType);
            cardName = itemView.findViewById(R.id.displayCardName);
            clientAddress = itemView.findViewById(R.id.displayAddress);
            orderStatus = itemView.findViewById(R.id.displayOrderStatus);
        }
    }
}
