package com.startandroid.trashstatf;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class CatalogFragment extends Fragment implements View.OnClickListener{


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getActivity().setTitle("Каталог карт");
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_catalog,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ImageButton imgButton1 = (getActivity()).findViewById(R.id.yourCard);
        ImageButton imgButton2 = (getActivity()).findViewById(R.id.travelCard);
        ImageButton imgButton3 = (getActivity()).findViewById(R.id.tigerCard);
        ImageButton imgButton4 = (getActivity()).findViewById(R.id.panasonicCard);
        ImageButton imgButton5 = (getActivity()).findViewById(R.id.troikaCard);
        ImageButton imgButton6 = (getActivity()).findViewById(R.id.rosneftCard);
        ImageButton imgButton7 = (getActivity()).findViewById(R.id.pensionCard);
        ImageButton imgButton8 = (getActivity()).findViewById(R.id.personCard);
        ImageButton imgButton9 = (getActivity()).findViewById(R.id.wagesCard);



        imgButton1.setOnClickListener(this);
        imgButton2.setOnClickListener(this);
        imgButton3.setOnClickListener(this);
        imgButton4.setOnClickListener(this);
        imgButton5.setOnClickListener(this);
        imgButton6.setOnClickListener(this);
        imgButton7.setOnClickListener(this);
        imgButton8.setOnClickListener(this);
        imgButton9.setOnClickListener(this);


    }


    // Обрабочик события - выбор карты из каталога ( открывается дополнительное окно с расширенной информацией)
    @Override
    public void onClick(View v) {
        SharedPreferences sharedPref = getActivity().getSharedPreferences("picCard",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        SharedPreferences cardInfo = getActivity().getSharedPreferences("cardInfo",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor2 = cardInfo.edit();

        SharedPreferences cardName = getActivity().getSharedPreferences("cardName",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor3 = cardName.edit();

        switch (v.getId()){
            case R.id.yourCard:
                editor.putInt("pic", R.drawable.yourself);
                editor2.putInt("info", R.string.yourCard);
                editor3.putString("name", "Своя карта");
                break;

            case R.id.travelCard:
                editor.putInt("pic", R.drawable.putevaya);
                editor2.putInt("info", R.string.putevayaCard);
                editor3.putString("name", "Путевая карта");
                break;

            case R.id.tigerCard:
                editor.putInt("pic", R.drawable.tigr);
                editor2.putInt("info", R.string.tigerCard);
                editor3.putString("name", "Карта Амурский Тигр");
                break;

            case R.id.panasonicCard:
                editor.putInt("pic", R.drawable.panasonic);
                editor2.putInt("info", R.string.panasonicCard);
                editor3.putString("name", "Карта Panasonic");
                break;

            case R.id.troikaCard:
                editor.putInt("pic", R.drawable.three);
                editor2.putInt("info", R.string.troikaCard);
                editor3.putString("name", "Карта МИР с транспортным приложением Тройка");
                break;

            case R.id.rosneftCard:
                editor.putInt("pic", R.drawable.rosneft);
                editor2.putInt("info", R.string.rosneftCard);
                editor3.putString("name", "Карта Россельхозбанк-Роснефть");
                break;

            case R.id.pensionCard:
                editor.putInt("pic", R.drawable.pens);
                editor2.putInt("info", R.string.pensionCard);
                editor3.putString("name", "Пенсионная карта");
                break;

            case R.id.personCard:
                editor.putInt("pic", R.drawable.person);
                editor2.putInt("info", R.string.personCard);
                editor3.putString("name", "Персональная карта");
                break;

            case R.id.wagesCard:
                editor.putInt("pic", R.drawable.payment);
                editor2.putInt("info", R.string.wagesCard);
                editor3.putString("name", "Индивидуальная зарплатная карта");
                break;

        }

        editor.commit();
        editor2.commit();
        editor3.commit();

        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.fragment_container,
                new ExtendedInfFragment()).commit();

    }
}
