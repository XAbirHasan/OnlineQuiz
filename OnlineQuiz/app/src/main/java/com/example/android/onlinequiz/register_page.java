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
import android.widget.EditText;
import android.widget.Toast;

public class register_page extends AppCompatActivity {

    public static Button next;
    public static EditText reg_name;
    public static EditText reg_id;
    public static EditText reg_password;
    public static EditText retype_password;


    SQLiteOpenHelper openHelper;
    SQLiteDatabase user_db;

    SQLiteOpenHelper openHelper_r;
    SQLiteDatabase user_db_r;

    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);
        go_to_page_1();
    }
    void go_to_page_1()
    {
        reg_name = (EditText)findViewById(R.id.reg_name);
        reg_id = (EditText)findViewById(R.id.reg_id);
        reg_password = (EditText)findViewById(R.id.reg_passWord);
        retype_password = (EditText)findViewById(R.id.retype_password);

        openHelper = new DataBase_for_user(this);
        openHelper_r = new DataBase_for_user(this);

        next = (Button)findViewById(R.id.register);
        next.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int flag = 0;

                        user_db = openHelper.getWritableDatabase();

                        user_db_r = openHelper_r.getReadableDatabase();

                        String user_email = reg_id.getText().toString();
                        String user_name = reg_name.getText().toString();
                        String user_password = reg_password.getText().toString();

                        cursor = user_db_r.rawQuery("SELECT * FROM " + DataBase_for_user.TABLE_NAME + " WHERE " + DataBase_for_user.COL_2 + "=?", new String[]{user_email} );
                        if(cursor != null)
                        {
                            if(cursor.getCount() > 0)
                            {
                                cursor.moveToNext();
                                Toast.makeText(register_page.this,"this email already registered",Toast.LENGTH_SHORT).show();
                                flag = 1;

                            }
                        }


                        if (reg_name.getText().toString().equals("") || reg_id.getText().toString().equals("") || reg_password.getText().toString().equals("") || retype_password.getText().toString().equals(""))
                        {
                            Toast.makeText(register_page.this, "fields are empty", Toast.LENGTH_SHORT).show();
                        }
                        else if (retype_password.getText().toString().equals(reg_password.getText().toString()))
                        {
                            if(flag == 0) {
                                insert_in_database(user_email, user_name, user_password);
                                Toast.makeText(register_page.this, "successfully register", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(register_page.this, login.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                        else
                        {
                            if(flag == 0)
                            {
                                Toast.makeText(register_page.this, " password don't match..! ", Toast.LENGTH_SHORT).show();
                            }
                        }

                    }
                }
        );
    }

    public void insert_in_database(String user_email, String user_name, String user_password)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataBase_for_user.COL_2, user_email);
        contentValues.put(DataBase_for_user.COL_3, user_name);
        contentValues.put(DataBase_for_user.COL_4, user_password);
        contentValues.put(DataBase_for_user.COL_5, "0");

        long id = user_db.insert(DataBase_for_user.TABLE_NAME, null, contentValues);
    }
}
