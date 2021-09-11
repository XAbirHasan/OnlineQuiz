package com.example.android.onlinequiz;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;

public class login extends AppCompatActivity {

    public static Button next;
    public static EditText mail_id;
    public static EditText password;
    public int attempts = 3;

    SQLiteOpenHelper openHelper;
    SQLiteDatabase user_db_r;

    Cursor cursor;

    DataBase_for_user dataBase_for_user;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        go_to_page_1();
    }

    void go_to_page_1()
    {
        mail_id = (EditText)findViewById(R.id.mail);
        password = (EditText)findViewById(R.id.password);
        next = (Button)findViewById(R.id.login);

        openHelper = new DataBase_for_user(this);
        user_db_r = openHelper.getReadableDatabase();


        next.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int flag = 0;
                        String longin_email = mail_id.getText().toString();
                        String login_password = password.getText().toString();
                        cursor = user_db_r.rawQuery("SELECT * FROM " + DataBase_for_user.TABLE_NAME + " WHERE " + DataBase_for_user.COL_2 + "=? AND " + DataBase_for_user.COL_4 + "=?" , new String[]{longin_email,login_password});

                        if(cursor != null)
                        {
                            if(cursor.getCount() > 0)
                            {
                                cursor.moveToNext();
                                flag = 1;
                                dataBase_for_user = new DataBase_for_user(getApplicationContext());
                                sqLiteDatabase = dataBase_for_user.getReadableDatabase();

                                Cursor cursor = dataBase_for_user.getContact(longin_email, sqLiteDatabase);
                                if (cursor.moveToFirst()) {
                                    MainActivity.user_sl = Integer.parseInt(cursor.getString(0));
                                    MainActivity.user_email = cursor.getString(1);
                                    MainActivity.user_name = cursor.getString(2);
                                    MainActivity.user_password = cursor.getString(3);
                                    MainActivity.user_score = cursor.getString(4);
                                }
                                Toast.makeText(login.this,"successfully login",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(login.this, page_1.class);
                                startActivity(intent);
                                finish();
                            }
                            else
                            {
                                Toast.makeText(login.this,"wrong..! email or password ",Toast.LENGTH_SHORT).show();
                            }
                        }
                        if( mail_id.getText() != null && password.getText() != null )
                        {
                            attempts--;
                        }
                        if( (mail_id.getText().toString().equals("") || password.getText().toString().equals("")) && attempts !=0) {
                            Toast.makeText(login.this,"fields are empty",Toast.LENGTH_SHORT).show();
                        }
                        if(attempts == 0 && flag == 0)
                        {
                            Toast.makeText(login.this,"register first",Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(login.this,register_page.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                }
        );
    }


}