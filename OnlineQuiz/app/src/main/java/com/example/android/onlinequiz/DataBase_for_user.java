package com.example.android.onlinequiz;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DataBase_for_user extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "register_for_onlineQuiz.db";
    public static final String TABLE_NAME = "register";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "Email";
    public static final String COL_3 = "Name";
    public static final String COL_4 = "Password";
    public static final String COL_5 = "Score";

    public DataBase_for_user(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " ("+COL_1+" INTEGER PRIMARY KEY AUTOINCREMENT,"+COL_2+" TEXT,"+COL_3+" TEXT,"+COL_4+" TEXT,"+COL_5+" Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int old_data, int new_data) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS" +TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public Cursor getContact(String sl_no, SQLiteDatabase sqLiteDatabase)
    {
        String[] projections = {COL_1,COL_2,COL_3, COL_4,COL_5};
        String selection = COL_2+" LIKE ?";
        String[] selection_args = {sl_no};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, projections, selection,selection_args,null,null,null);
        return cursor;
    }

    public void updateData(int sl, String email,String name,String pass, String score, SQLiteDatabase sqLiteDatabase)
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, sl);
        contentValues.put(COL_2, email);
        contentValues.put(COL_3, name);
        contentValues.put(COL_4, pass);
        contentValues.put(COL_5, score);
        sqLiteDatabase.update(TABLE_NAME,contentValues, COL_2+" LIKE ?",new String[]{email});
    }

    public Cursor getAlluserInfo(SQLiteDatabase sqLiteDatabase)
    {

        String[] projections = {COL_3,COL_5};
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, projections,null,null,null,null,null);

        return cursor;
    }
}
