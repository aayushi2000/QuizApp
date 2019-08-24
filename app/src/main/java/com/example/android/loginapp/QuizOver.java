package com.example.android.loginapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class QuizOver extends Fragment {
    String score;
    TextView SCORE;

    public QuizOver()
    {}

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View rootview = inflater.inflate(R.layout.game_over, container, false);
        Bundle bundle = getArguments();
        score = bundle.getString("Score");
        SCORE = rootview.findViewById(R.id.score);
        SCORE.setText(score);
        return  rootview;

    }
}
