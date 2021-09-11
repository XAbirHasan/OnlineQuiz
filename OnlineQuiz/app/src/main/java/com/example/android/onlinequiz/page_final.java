package com.example.android.onlinequiz;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class page_final extends AppCompatActivity {

    public static Button exit;
    public static TextView show_score;
    public static ImageView show_emoji;
    public static int image_file;

    public static TextView user_name;
    public static Intent intent;


    DataBase_for_user dataBase_for_user;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_final);
        show_score = (TextView)findViewById(R.id.show_score);

        show_emoji = (ImageView)findViewById(R.id.showEmoji);

        user_name = (TextView)findViewById(R.id.user_name6) ;
        user_name.setText(MainActivity.user_name);

        if(MainActivity.quiz_score == 5)
        {
            image_file = getResources().getIdentifier("@drawable/emoji100", null, this.getPackageName());
        }
        else if(MainActivity.quiz_score == 4)
        {
            image_file = getResources().getIdentifier("@drawable/emoji80", null, this.getPackageName());
        }
        else if(MainActivity.quiz_score == 3)
        {
            image_file = getResources().getIdentifier("@drawable/emoji60", null, this.getPackageName());
        }
        else if(MainActivity.quiz_score == 2)
        {
            image_file = getResources().getIdentifier("@drawable/emoji40", null, this.getPackageName());
        }
        else if(MainActivity.quiz_score == 1)
        {
            image_file = getResources().getIdentifier("@drawable/emoji20", null, this.getPackageName());
        }
        else if(MainActivity.quiz_score == 0)
        {
            image_file = getResources().getIdentifier("@drawable/emoji0", null, this.getPackageName());
        }

        show_emoji.setImageResource(image_file);
        show_score.setText("Your Score : " + Integer.toString(MainActivity.quiz_score*20) + "  % ");
        exit();

        int user_score = Integer.parseInt(MainActivity.user_score);
        int score = MainActivity.quiz_score;
        if(user_score <score)
        {
            dataBase_for_user = new DataBase_for_user(getApplicationContext());
            sqLiteDatabase = dataBase_for_user.getReadableDatabase();
            dataBase_for_user.updateData(MainActivity.user_sl,MainActivity.user_email,MainActivity.user_name,MainActivity.user_password,Integer.toString(score),sqLiteDatabase);
        }

        MainActivity.quiz_score = 0;
    }

    public void exit()
    {
        exit = (Button)findViewById(R.id.exit);
        exit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(page_final.this,Show_all_score.class);
                        startActivity(intent);
                        finish();
                    }
                }
        );
    }

}
