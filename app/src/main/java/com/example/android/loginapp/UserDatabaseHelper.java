package com.example.android.loginapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class UserDatabaseHelper extends SQLiteOpenHelper {
    SQLiteDatabase db;

    private static String Database_name= "QuizDataBase.db";
    private static String User_table= "UserInfo";
    private  static final  String HS_table = "HighScore";

private static final  String Create_User_Table = "CREATE TABLE " + User_table + "(User_Name TEXT," + "Name TEXT," + "Password TEXT," + " s1 NUMBER, " + "s2 NUMBER," +" s3 NUMBER);";
private static final String Create_High_Score_Table = "CREATE TABLE " + HS_table + " (Score NUMBER," + "Name TEXT," + " Username TEXT," + " Category TEXT);";

    public UserDatabaseHelper( Context context) {
        super(context,Database_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

db.execSQL(Create_User_Table);
db.execSQL(Create_High_Score_Table);
        this.db=db;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("Drop table if exists " + User_table +";");
        db.execSQL("Drop table if exists " + HS_table+";");
this.onCreate(db);
    }
    public void insertUserData(UserInfo userinfo){
        db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put("User_Name",userinfo.getUsername());
        values.put("Name", userinfo.getName());
        values.put("Password", userinfo.getPassword());
        values.put("s1",userinfo.getscore1());
        values.put("s2",userinfo.getscore2());
        values.put("s3", userinfo.getscore3());

        db.insert(User_table,null,values);
        db.close();
    }
    public boolean searchUname(String Uname) {
        db = this.getReadableDatabase();
        Cursor cursor = (Cursor) db.rawQuery("SELECT User_Name from " + User_table, null);
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(0).equals(Uname)) ;
                {
                    return true;
                }
            } while (cursor.moveToNext());
        }
            db.close();
            return false;

    }
        public int searchLogin(String uname, String pass)
        {
            db = getReadableDatabase();
            Cursor cursor = db.rawQuery("select User_Name, Password from " + User_table, null);
            if (cursor.moveToFirst())
            {
                do{
                    if(cursor.getString(0).equals(uname))
                    {
                        if(cursor.getString(1).equals(pass))
                        {
                            return 2;
                        }
                        return 1;
                    }
                }while(cursor.moveToNext());
        }
        db.close();
            return 0;

    }


public UserInfo getProfile(String uname)
{
    db= getReadableDatabase();
    UserInfo userInfo= new UserInfo();
    Cursor cursor = db.rawQuery("select User_name, Name, Password, s1, s2, s3 from " + User_table , null);
    if(cursor.moveToFirst())
    {
        do
            {
                if(cursor.getString(0).equals(uname))
                {
                    userInfo.setUsername(cursor.getString(0));
                    userInfo.setName(cursor.getString(1));
                    userInfo.setPassword(cursor.getString(2));
                    userInfo.setScore1(cursor.getInt(3));
                    userInfo.setscore2(cursor.getInt(4));
                    userInfo.setscore3(cursor.getInt(5));
                    break;
                }

        }while(cursor.moveToNext());
    }
    db.close();
    return userInfo;

    }
public UserInfo updateUserProfile (UserInfo userinfo, int s){
        Integer[] score_Array = new Integer[4];
        score_Array[0] = userinfo.getscore1();
        score_Array[1] = userinfo.getscore2();
        score_Array[2] = userinfo.getscore3();
        score_Array[3] = s;
    Arrays.sort(score_Array, Collections.<Integer>reverseOrder());
    userinfo.setScore1(score_Array[0]);
    userinfo.setscore2(score_Array[1]);
    userinfo.setscore3(score_Array[2]);
    db = this.getReadableDatabase();
    ContentValues values = new ContentValues();
    values.put("s1",score_Array[0]);
    values.put("s2",score_Array[1]);
    values.put("s3",score_Array[2]);
    db.update(User_table, values, "User_/name = ?", new String[] {userinfo.getUsername()});
    db.close();
    return userinfo;

}
public ArrayList<HighScoreClass> getHighScore()
{
    HighScoreClass highScoreClass;
    ArrayList<HighScoreClass> arrayHighScore = new ArrayList<HighScoreClass>();
    db = getReadableDatabase();
    Cursor cursor = db.rawQuery("select Score, Name, Username, Category from " +HS_table+ " Order by Score DESC ", null );
    if(cursor.moveToFirst())
    {
        do{
            highScoreClass = new HighScoreClass();
            highScoreClass.setScore(cursor.getInt(0));
            highScoreClass.setName(cursor.getString(1));
            highScoreClass.setUname(cursor.getString(2));
            highScoreClass.setCat(cursor.getString(3));
            arrayHighScore.add(highScoreClass);
        }while (cursor.moveToNext());

    }
    db.close();
    return arrayHighScore;
}
public void insertHighScore(int score, String username, String name, String cat)
{
    ArrayList<HighScoreClass> arrayHighScore = new ArrayList<HighScoreClass>();
    HighScoreClass highScoreClass;
    int i=0;
    db =this.getWritableDatabase();
    ContentValues values = new ContentValues();
    values.put("Score",score);
    values.put("Name",name);
    values.put("Username",username);
    values.put("Category",cat);
    db.insert(HS_table,null,values);
    db.close();

    db = getReadableDatabase();
    Cursor cursor = db.rawQuery("select Score , Name, Username, Category from  "+HS_table+" Order by Score DESC",null);
    if(cursor.moveToFirst())
    {
        do {
            highScoreClass = new HighScoreClass();
            highScoreClass.setScore(cursor.getInt(0));
            highScoreClass.setName(cursor.getString(1));
            highScoreClass.setUname(cursor.getString(2));
            highScoreClass.setCat(cursor.getString(3));
            arrayHighScore.add(highScoreClass);
        }while(cursor.moveToNext());
    }
    Collections.sort(arrayHighScore);
    db.close();
    db = this.getWritableDatabase();
    db.execSQL("delete from "+ HS_table);

    db=this.getWritableDatabase();
    while(i<arrayHighScore.size() && i<=10)
    {
        values = new ContentValues();
        values.put("Score",arrayHighScore.get(i).getScore());
        values.put("Name",arrayHighScore.get(i).getName());
        values.put("Username",arrayHighScore.get(i).getUname());
        values.put("Category",arrayHighScore.get(i).getCat());
        db.insert(HS_table,null,values);
        i++;
    }
    db.close();
}
}