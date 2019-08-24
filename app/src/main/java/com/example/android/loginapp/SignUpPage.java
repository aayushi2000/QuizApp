package com.example.android.loginapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpPage extends Fragment {

    View rootview;
    public SignUpPage()
    {

    }
    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
    {
        rootview = inflater.inflate(R.layout.signup_page,container,false);
        final EditText name = rootview.findViewById(R.id.signupname_edit);
        final EditText username= rootview.findViewById(R.id.signupusername_edit);
        final EditText password = rootview.findViewById(R.id.signuppassword_edit);
        Button createAccount =  rootview.findViewById(R.id.create_account);

        createAccount.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                UserDatabaseHelper userDatabaseHelper = new UserDatabaseHelper(getActivity());
                if (userDatabaseHelper.searchUname(username.getText().toString()))
                {
                    Toast.makeText(getActivity(),"User already exists !!", Toast.LENGTH_SHORT).show();
                }
                else {
                    UserInfo userInfo = new UserInfo();
                    userInfo.setName(name.getText().toString());
                    userInfo.setUsername(username.getText().toString());
                    userInfo.setPassword(password.getText().toString());
                    userInfo.setScore1(0);
                    userInfo.setscore2(0);
                    userInfo.setscore3(0);

                    userDatabaseHelper.insertUserData(userInfo);
                    Toast.makeText(getActivity(), "Account created successfully!!", Toast.LENGTH_LONG).show();
                    ((LoginPage)getActivity()).accountCreated(username.getText().toString(), password.getText().toString());
                }
            }
        });
        return rootview;
    }
}
