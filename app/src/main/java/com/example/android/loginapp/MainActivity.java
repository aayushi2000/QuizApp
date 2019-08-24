
package com.example.android.loginapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements QuizFragment.FinalScore{

    Button sportButton, scienceButton, gkButton, highscore, profile;
    int score;
    Boolean pro_frag, quiz_frag, over_frag, high_frag;
    UserInfo thisuser;
    UserDatabaseHelper userdatabasehelper;
    ProfileFragment profileFragment;
    HighScoreFragment highScoreFragment;
    QuizOver quizover;
    QuizFragment quizfragment;
    String category, username;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pro_frag = quiz_frag = over_frag = high_frag = false;
        username = getIntent().getStringExtra("Username");
        thisuser = new UserInfo();
        userdatabasehelper = new UserDatabaseHelper(this);
        thisuser = userdatabasehelper.getProfile(username);

        sportButton = findViewById(R.id.sports_button);
        gkButton = findViewById(R.id.gk_button);
        scienceButton = findViewById(R.id.science_button);
        highscore = findViewById(R.id.highscore_button);
        profile = findViewById(R.id.profile_button);

        sportButton.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View v) {


                                               quizfragment = new QuizFragment();
                                               category = "sports";
                                               Bundle bundle = new Bundle();
                                               bundle.putString("category", category);
                                               quizfragment.setArguments(bundle);
                                               RelativeLayout quizContainer = findViewById(R.id.quiz_container);
                                               quizContainer.setClickable(true);
                                               quiz_frag = true;
                                               FragmentManager fragmentManager = getSupportFragmentManager();
                                               FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                               fragmentTransaction.add(R.id.quiz_container, quizfragment);
                                               fragmentTransaction.commit();
                                           }
                                       }


        );
        scienceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                quizfragment = new QuizFragment();
                category = "science";
                Bundle bundle = new Bundle();
                bundle.putString("category", category);
                quizfragment.setArguments(bundle);
                RelativeLayout quizContainer = findViewById(R.id.quiz_container);
                quizContainer.setClickable(true);
                quiz_frag = true;
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.quiz_container, quizfragment);
                fragmentTransaction.commit();
            }
        });
        gkButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                quizfragment = new QuizFragment();
                category = "gk";
                Bundle bundle = new Bundle();
                bundle.putString("category", category);
                quizfragment.setArguments(bundle);
                RelativeLayout quizContainer = findViewById(R.id.quiz_container);
                quizContainer.setClickable(true);
                quiz_frag = true;
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.add(R.id.quiz_container, quizfragment);
                fragmentTransaction.commit();
            }
        });

        highscore.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                highScoreFragment = new HighScoreFragment();
                getSupportFragmentManager().beginTransaction().add(R.id.quiz_container, highScoreFragment).commit();
                high_frag=true;
            }
        }
    );
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                profileFragment = new ProfileFragment();
                profileFragment.setProfileData(thisuser);
                getSupportFragmentManager().beginTransaction().add(R.id.quiz_container, profileFragment).commit();

                pro_frag = true;

            }
        });
    }
    @Override
public void getFinalScore(int s)
    {
    score = s;
    UserDatabaseHelper userdatabasehelper = new UserDatabaseHelper(this);
    thisuser = new UserInfo(userdatabasehelper.updateUserProfile(thisuser, s));
    }

    public void overMainMenu(View view) {

        getSupportFragmentManager().beginTransaction().remove(quizover).commit();
        over_frag = true;
    }

    public void back_HighScore(View view)
    {
        getSupportFragmentManager().beginTransaction().remove(highScoreFragment).commit();

    }
    public void profile_back(View view)
    {
        getSupportFragmentManager().beginTransaction().remove(profileFragment).commit();
        pro_frag=true;
    }
    public void LOGOUT(View view)
    {
        sp = getSharedPreferences("key",0);
        SharedPreferences.Editor editor= sp.edit();
        editor.remove("Login").commit();
        editor.remove("Username").commit();
        editor.remove("Password").commit();
        startActivity(new Intent(MainActivity.this,LoginPage.class));
    }

    @Override
    public void onBackPressed()
    {
        if(pro_frag)
        {

        getSupportFragmentManager().beginTransaction().remove(profileFragment).commit();
        pro_frag = false;
        }
        if(quiz_frag)
        {

            getSupportFragmentManager().beginTransaction().remove(quizfragment).commit();
            quiz_frag = false;
        }
        if(over_frag)
        {

            getSupportFragmentManager().beginTransaction().remove(quizover).commit();
            over_frag = false;
        }
        if(high_frag)
        {

            getSupportFragmentManager().beginTransaction().remove(highScoreFragment).commit();
            high_frag = false;
        }
        else
        {
            super.onBackPressed();
        }


    }

}
