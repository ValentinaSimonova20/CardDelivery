package com.startandroid.trashstatf;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class ExtendedInfFragment extends Fragment {





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sp3 =  getActivity().getSharedPreferences("cardName",  getActivity().MODE_PRIVATE);
        String title = sp3.getString("name", "");

        getActivity().setTitle(title);
        setHasOptionsMenu(true);


        return inflater.inflate(R.layout.fragment_cardinf,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SharedPreferences sp =  getActivity().getSharedPreferences("picCard",  getActivity().MODE_PRIVATE);
        int myIntValue = sp.getInt("pic", -1);

        SharedPreferences sp2 =  getActivity().getSharedPreferences("cardInfo",  getActivity().MODE_PRIVATE);
        int myIntValue2 = sp2.getInt("info", -1);


        // Установка описания карты
        TextView cardInfo = getActivity().findViewById(R.id.cardInfo);
        cardInfo.setText(myIntValue2);

        // Установка изображения карты
        ImageView iv = getActivity().findViewById(R.id.imageView2);
        iv.setImageResource(myIntValue);


    }
}
