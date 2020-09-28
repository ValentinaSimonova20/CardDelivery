package com.startandroid.trashstatf;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class RateAppFragment extends Fragment {

    TextView rateCount, showRating;
    EditText review;
    Button submit;
    RatingBar ratingBar;
    float rateValue;
    String temp;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("Оценка сервиса");
        setHasOptionsMenu(true);

        return inflater.inflate(R.layout.fragment_rateapp,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rateCount = getActivity().findViewById(R.id.rateCount);
        ratingBar = getActivity().findViewById(R.id.ratingBar);
        review = getActivity().findViewById(R.id.review);
        submit = getActivity().findViewById(R.id.submitBtn);
        showRating = getActivity().findViewById(R.id.showRating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                rateValue = ratingBar.getRating();

                if(rateValue<=1 && rateValue>0)
                    rateCount.setText("Низкая оценка "+rateValue +"/5");
                else if(rateValue<=2 && rateValue>1)
                    rateCount.setText("Средняя оценка "+rateValue +"/5");
                else if(rateValue<=3 && rateValue>2)
                    rateCount.setText("Хорошая оценка "+rateValue +"/5");
                else if(rateValue<=4 && rateValue>3)
                    rateCount.setText("Очень хорошая оценка "+rateValue +"/5");
                else if(rateValue<=5 && rateValue>4)
                    rateCount.setText("Отличная оценка "+rateValue +"/5");
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = rateCount.getText().toString();
                showRating.setText("Ваша оценка: \n"+temp+"\n"+review.getText());
                review.setText("");
                ratingBar.setRating(0);
                rateCount.setText("");
            }
        });








    }
}
