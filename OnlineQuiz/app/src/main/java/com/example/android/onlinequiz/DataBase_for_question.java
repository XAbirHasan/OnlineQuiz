package com.example.android.onlinequiz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBase_for_question extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Question.db";
    public static final String TABLE_NAME = "question";
    public static final String COL_1 = "SL";
    public static final String COL_2 = "Question";
    public static final String COL_3 = "A";
    public static final String COL_4 = "B";
    public static final String COL_5 = "C";
    public static final String COL_6 = "CorrectAns";


    public static String DB_PATH = null;

    public DataBase_for_question(Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";
        Log.e("Path 1", DB_PATH);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " ("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_2+" TEXT,"+COL_3+" TEXT,"+COL_4+" Text,"+COL_5+" Text,"+COL_6+" Text)");
    }

    public Cursor getContact(String sl_no, SQLiteDatabase sqLiteDatabase)
    {
        String[] projections = {COL_2,COL_3, COL_4,COL_5,COL_6};
        String selection = COL_1+" LIKE ?";
        String[] selection_args = {sl_no};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, projections, selection,selection_args,null,null,null);
        return cursor;
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old_data, int new_data) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}

