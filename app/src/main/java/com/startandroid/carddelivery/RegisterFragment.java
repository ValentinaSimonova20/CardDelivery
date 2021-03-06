package com.startandroid.carddelivery;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.rengwuxian.materialedittext.MaterialEditText;


public class RegisterFragment extends Fragment {

    DatabaseHelper dbHelper;

    MaterialEditText email;
    MaterialEditText pass;
    MaterialEditText name;
    MaterialEditText surname;
    MaterialEditText phone;
    MaterialEditText passport;

    Button registerButton;

    RelativeLayout root;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Регистрация");
        setHasOptionsMenu(true);
        dbHelper = new DatabaseHelper(getActivity());
        return inflater.inflate(R.layout.fragment_registere,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        email = getView().findViewById(R.id.emailField);
        pass = getView().findViewById(R.id.passField);
        name = getView().findViewById(R.id.nameField);
        phone = getView().findViewById(R.id.phoneField);
        surname = getView().findViewById(R.id.surnameField);
        passport = getView().findViewById(R.id.passportField);
        root = getView().findViewById(R.id.root_element);

        registerButton = getView().findViewById(R.id.registerButton);

        View.OnClickListener listenerButton = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(email.getText().toString())){
                    Snackbar.make(root, "Введите вашу почту", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(name.getText().toString())){
                    Snackbar.make(root, "Введите ваше имя", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(phone.getText().toString())){
                    Snackbar.make(root, "Введите ваш телефон", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(surname.getText().toString())){
                    Snackbar.make(root, "Введите вашу фамилию", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(passport.getText().toString())){
                    Snackbar.make(root, "Введите ваши пасспортные данные", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                if(pass.getText().toString().length() < 5){
                    Snackbar.make(root, "Введите пароль, длиной более 5 символов", Snackbar.LENGTH_SHORT).show();
                    return;
                }


                //Регистрация пользователя
                User user = new User();
                user.setEmail(email.getText().toString());
                user.setName(name.getText().toString());
                user.setPass(pass.getText().toString());
                user.setPhone(phone.getText().toString());
                user.setSurname(surname.getText().toString());
                user.setPassport(passport.getText().toString());


                 //Проверка на то, есть ли пользователь с таким логином в бд
                if(dbHelper.isClientExists(user.getEmail())){
                    //Если есть, то не добавляем в таблицу Users
                    Snackbar.make(root,"Данный пользователь уже зарегестрирован! Введите другой email", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                else{
                    Snackbar.make(root,"Вы успешно зарегестрировались. Перейдите в пункт меню авторизация для продолжения работы под своей учетной записью.", Snackbar.LENGTH_SHORT).show();
                    dbHelper.addNewUser(user.getPass(),user.getEmail(),user.getPhone(),user.getName(), user.getSurname(), user.getPassport());
                }




            }
        };

        registerButton.setOnClickListener(listenerButton);


    }
}
