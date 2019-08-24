package com.example.android.loginapp;

public class UserInfo {
    String name;
    String Username;
    String password;
    int score1;
    int score2;
    int score3;

    public UserInfo(){}
    public UserInfo(UserInfo u)
    {
        name= u.getName();
        Username = u.getUsername();
        password = u.getPassword();
        score1 = u.getscore1();
        score2 = u.getscore2();
        score3 =u.getscore3();
    }

    public String getName(){ return name;}

    public void setName(String name) {this.name = name;}

    public String getUsername(){ return Username;}

    public void setUsername(String Username){this.Username= Username;}

    public String getPassword(){return password;}

    public void setPassword(String password) {this.password = password;}

    public int getscore1(){return score1;}

    public void setScore1(int score1) {this.score1 = score1;}

    public int getscore2(){return score2;}

    public void setscore2(int score2) {this.score2 = score2;}

    public int getscore3(){return score3;}

    public void setscore3(int score3) {this.score3 = score3;}

}
