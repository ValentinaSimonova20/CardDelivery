package com.startandroid.trashstatf;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



public class OrderCardFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    Spinner cardTypes,cardNames;
    private DatabaseHelper dbHelper;
    SharedPreferences loginPref;
    Button orderCardButton;

    ArrayAdapter<CharSequence> adapter2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Заказать карту");
        dbHelper = new DatabaseHelper(getActivity());
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_ordercard,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        cardTypes = getView().findViewById(R.id.cardType);

        // Заполнить первый выпадающий список типами карт из strings
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getActivity(),R.array.typeOfCards,android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        cardTypes.setAdapter(adapter1);
        cardTypes.setOnItemSelectedListener(this);

        // выпадающий список с названием карт
        cardNames = getView().findViewById(R.id.cardName);

        // кнопка "Заказать".
        // производится запись в базу данных
        orderCardButton = getView().findViewById(R.id.orderButton);
        orderCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                dbHelper = new DatabaseHelper(getActivity());
                // считываем все введенные значения в поля формы

                // город
                EditText cityField = getView().findViewById(R.id.cityField);
                String city = cityField.getText().toString();

                String cardType = String.valueOf(cardTypes.getSelectedItem());
                String cardName = String.valueOf(cardNames.getSelectedItem());

                // улица
                EditText streetField =  getView().findViewById(R.id.streetField);
                String street = streetField.getText().toString();

                // номер дом
                EditText homeField =  getView().findViewById(R.id.homeField);
                String home = homeField.getText().toString();

                // корпус дома
                EditText homeKorpField =  getView().findViewById(R.id.korpField);
                String homeKorp = homeKorpField.getText().toString();

                // подъезд
                EditText entranceField =  getView().findViewById(R.id.entranceField);
                String entrance = entranceField.getText().toString();

                // квартира
                EditText flatField =  getView().findViewById(R.id.flatField);
                String flat = flatField.getText().toString();

                if(!isFieldsValidate(city,home, street, homeKorp, entrance, flat)){
                    Toast.makeText(getActivity(),"Вы не заполнили все поля",Toast.LENGTH_LONG).show();
                    return;
                }


                loginPref = getActivity().getSharedPreferences("login", Context.MODE_PRIVATE);
                String userLogin = loginPref.getString("UsersLogin","");
                dbHelper.addNewOrder(userLogin, cardType, cardName, city, street, home, homeKorp, entrance, flat, "Заказ на обработке");
                Toast.makeText(getActivity(),"Заказ осуществлен!",Toast.LENGTH_LONG).show();
            }
        });




    }

    private boolean isFieldsValidate(String city, String home, String street, String korp, String entrance, String flat){
        boolean flag = true;
        if(city.equals("") | home.equals("") | street.equals("") | korp.equals("") | entrance.equals("") | flat.equals("")) flag = false;
        return flag;
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String cardType = parent.getItemAtPosition(position).toString();
        switch(cardType) {
            case "Дебетовая карта":
                adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.debitCards,android.R.layout.simple_spinner_item);
                createSecondSpinner();
                break;
            case "Кредитная карта":
                adapter2 = ArrayAdapter.createFromResource(getActivity(),R.array.creditCards,android.R.layout.simple_spinner_item);
                createSecondSpinner();
                break;
        }

    }


    public void createSecondSpinner(){
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter2.notifyDataSetChanged();
        cardNames.setAdapter(adapter2);
        cardNames.setOnItemSelectedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
