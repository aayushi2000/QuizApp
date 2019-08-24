package com.example.android.loginapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class HighscoreAdapter extends ArrayAdapter<HighScoreClass> {
    Context context;
    private ArrayList<HighScoreClass> obj_array;

    public HighscoreAdapter(Context context, int textViewResourceId, ArrayList<HighScoreClass> items) {

        super(context, textViewResourceId, items);
        this.context = context;
        this.obj_array = items;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.highscore_adapter, null);

        }
        HighScoreClass o = obj_array.get(position);
        if (o != null) {
            TextView score = v.findViewById(R.id.score_text);
            TextView name = v.findViewById(R.id.name_text);
            TextView rank = v.findViewById(R.id.rank_text);
            TextView cat = v.findViewById(R.id.category_text);

            rank.setText(String.valueOf(position + 1));
            score.setText(String.valueOf(o.getScore()));
            name.setText(o.getName());
            cat.setText(o.getCat());
        }
        return v;


    }
}