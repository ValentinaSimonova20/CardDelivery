package com.startandroid.trashstatf;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClientOrdersFragment extends Fragment {

    private SharedPreferences loginPref;
    private DatabaseHelper dbHelper;

    RecyclerView recyclerView;
    ArrayList<Order> orders  = new ArrayList<>();;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper = new DatabaseHelper(getActivity());
        getActivity().setTitle("Мои заказы");
        setHasOptionsMenu(true);


        return inflater.inflate(R.layout.fragment_clientorders,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerView = getActivity().findViewById(R.id.recview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        loginPref = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String userLogin = loginPref.getString("UsersLogin","");

        Cursor cursor = dbHelper.readAllClientOrders(userLogin);

        while (cursor.moveToNext())
        {

            Order order = new Order(cursor.getString(2),cursor.getString(3),
                    cursor.getString(4),cursor.getString(5),cursor.getString(6),
                    cursor.getString(7),cursor.getString(8),cursor.getString(9),
                    cursor.getString(10));


           orders.add(order);
        }

        MyAdapter adapter = new MyAdapter(orders);
       recyclerView.setAdapter(adapter);



    }
}
