package com.example.android.loginapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

public class HighScoreFragment extends Fragment {
    ListView listview;
    ArrayList<HighScoreClass> arrayList;
    UserDatabaseHelper userDatabaseHelper;

    public HighScoreFragment()
    {}
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        View rootview = inflater.inflate(R.layout.high_score_layout,container,false);

        listview = rootview.findViewById(R.id.highscore_list);
        userDatabaseHelper = new UserDatabaseHelper(getActivity());
        final HighscoreAdapter highscoreAdapter  = new HighscoreAdapter(getActivity(),R.layout.highscore_adapter, userDatabaseHelper.getHighScore());
        listview.setAdapter(highscoreAdapter);
        return rootview;

    }
}
