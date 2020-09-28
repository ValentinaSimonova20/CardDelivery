package com.startandroid.carddelivery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment {



    private DatabaseHelper dbHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Мой профиль");
        setHasOptionsMenu(true);
        dbHelper = new DatabaseHelper(getActivity());
        return inflater.inflate(R.layout.fragment_profile,container,false);
    }




    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView clientName = getView().findViewById(R.id.clientName);
        TextView clientSurname = getView().findViewById(R.id.clientSurname);
        TextView clientMail = getView().findViewById(R.id.clientMail);
        TextView clientPassport = getView().findViewById(R.id.clientPassport);

        SharedPreferences loginPref = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
        String userLogin = loginPref.getString("UsersLogin","");

        // Заполнить информацию пользователя
        clientMail.setText(userLogin);
        clientName.setText(dbHelper.getName(userLogin));
        clientSurname.setText(dbHelper.getSurname(userLogin));
        clientPassport.setText(dbHelper.getPassport(userLogin));







    }


}
