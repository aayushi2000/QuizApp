package com.example.android.loginapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    TextView n, s1,s2,s3;
    String name;
    int ss1 ,ss2 ,ss3;
    SharedPreferences sp;
    public ProfileFragment(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview= inflater.inflate(R.layout.profile_layout,container, false);

        n=rootview.findViewById(R.id.name);
        s1 = rootview.findViewById(R.id.s1);
        s2 = rootview.findViewById(R.id.s2);
        s3 = rootview.findViewById(R.id.s3);



        n.setText(name);
        s1.setText(String.valueOf(ss1));
        s2.setText(String.valueOf(ss2));
        s3.setText(String.valueOf(ss3));

        return rootview;
    }

    public void setProfileData(UserInfo userInfo)
    {
        name = userInfo.getName();
        ss1 = userInfo.getscore1();
        ss2 = userInfo.getscore2();
        ss3 = userInfo.getscore3();
    }

}
