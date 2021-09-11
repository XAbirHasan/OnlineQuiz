package com.example.android.onlinequiz;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Show_all_score extends AppCompatActivity {

    ListView listView;
    DataBase_for_user dataBase_for_user;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;

    List_adapter list_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_all_score);
        makeList();
    }
    public void makeList()
    {
        listView = (ListView)findViewById(R.id.list_all);
        list_adapter = new List_adapter(getApplicationContext(), R.layout.row);
        listView.setAdapter(list_adapter);

        dataBase_for_user = new DataBase_for_user(getApplicationContext());
        sqLiteDatabase = dataBase_for_user.getReadableDatabase();
        cursor = dataBase_for_user.getAlluserInfo(sqLiteDatabase);
        if(cursor.moveToFirst())
        {
            do
            {
                String name, score;
                name = cursor.getString(0);
                score = cursor.getString(1);
                Data_info_store  data = new Data_info_store(name,Integer.parseInt(score));
                list_adapter.add(data);

            }while (cursor.moveToNext());
        }

    }
}
