package com.example.android.onlinequiz;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    public static int quiz_score;
    public static int user_sl;
    public static String user_name;
    public static String user_email;
    public static String user_password;
    public static String user_score;

    public static Button login;
    public static Button register;
    SQLiteOpenHelper openHelper;
    SQLiteDatabase user_db;

    SQLiteOpenHelper openHelper_r;
    SQLiteDatabase user_db_r;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        go_to_login();
        go_to_register();
        insertDatabaseQuestion();
    }

    public void go_to_login()
    {
        login = (Button)findViewById(R.id.loginButton);

        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                       Intent intent = new Intent(MainActivity.this,login.class);
                        startActivity(intent);

                    }
                }
        );
    }
    public void go_to_register()
    {
        register = (Button)findViewById(R.id.registerButton);
        register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(MainActivity.this,register_page.class);
                        startActivity(intent);

                    }
                }
        );
    }

    public void insertDatabaseQuestion()
    {
        openHelper_r = new DataBase_for_question(this);
        openHelper = new DataBase_for_question(this);
        user_db = openHelper.getWritableDatabase();
        user_db_r = openHelper_r.getReadableDatabase();

        cursor = user_db_r.rawQuery("SELECT * FROM " + DataBase_for_question.TABLE_NAME + " WHERE " + DataBase_for_question.COL_2 + "=?", new String[]{"1. Where is mount Everest ?"} );
        if(cursor != null) {
            if (cursor.getCount() > 0)
            {
                cursor.moveToNext();
            }
            else
            {
                insert_in_database("1. Where is mount Everest ?", "London","Nepal","Nederland","Nepal");
                insert_in_database("2. Who discovered Mount Everest?", "George Everest", "Jordan Romero", "Hilari Clinton", "George Everest");
                insert_in_database("3. What is the height of Mount Everest?", "8048 meters", "8008 meters", "8848 meters", "8848 meters");
                insert_in_database("4. When Mount Everest is discovered?", "1841", "1851", "1861","1841");
                insert_in_database("5. Who was the first pair to reach the    peak of Mount Everest ?","Michel and Johnson","Edmund Hilary and Tenzing Norgay","Edmund Hilar and Tenz Norgay","Edmund Hilary and Tenzing Norgay");
            }
        }

    }

    public void insert_in_database(String question, String optionA, String optionB,String optionC, String Ans)
    {
        ContentValues contentValues = new ContentValues();

        contentValues.put(DataBase_for_question.COL_2, question);
        contentValues.put(DataBase_for_question.COL_3, optionA);
        contentValues.put(DataBase_for_question.COL_4, optionB);
        contentValues.put(DataBase_for_question.COL_5, optionC);
        contentValues.put(DataBase_for_question.COL_6, Ans);

        long id = user_db.insert(DataBase_for_question.TABLE_NAME, null, contentValues);
    }
}
