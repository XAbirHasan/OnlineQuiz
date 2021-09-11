package com.example.android.onlinequiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class page_5 extends AppCompatActivity {

    public static Button next;
    public static RadioGroup question;
    public static RadioButton get_ans;
    public static Intent intent;

    DataBase_for_question dataBase_for_question;
    SQLiteDatabase sqLiteDatabase;


    public static TextView qn;
    public static TextView op_A;
    public static TextView op_B;
    public static TextView op_C;

    public static TextView user_name;

    public static String question_no;
    public static String option_A;
    public static String option_B;
    public static String option_C;
    public static String option_Ans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_5);
        go_to_page_final();
    }

    void go_to_page_final()
    {

        qn = (TextView)findViewById(R.id.q5);
        op_A = (TextView)findViewById(R.id.a5);
        op_B = (TextView)findViewById(R.id.b5);
        op_C = (TextView)findViewById(R.id.c5);

        user_name = (TextView)findViewById(R.id.user_name5) ;
        user_name.setText(MainActivity.user_name);


        dataBase_for_question = new DataBase_for_question(getApplicationContext());
        sqLiteDatabase = dataBase_for_question.getReadableDatabase();
        Cursor cursor = dataBase_for_question.getContact("5", sqLiteDatabase);
        if (cursor.moveToFirst())
        {
            question_no = cursor.getString(0);
            option_A = cursor.getString(1);
            option_B = cursor.getString(2);
            option_C = cursor.getString(3);
            option_Ans = cursor.getString(4);

        }
        qn.setText(question_no);
        op_A.setText(option_A);
        op_B.setText(option_B);
        op_C.setText(option_C);

        next = (Button)findViewById(R.id.next_5);
        question = (RadioGroup)findViewById(R.id.question5);



        next.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        intent = new Intent(page_5.this,page_final.class);

                        get_ans = (RadioButton)findViewById(question.getCheckedRadioButtonId());

                        if(get_ans.getText().toString().equals(option_Ans)) {
                            MainActivity.quiz_score++;
                            startActivity(intent);
                            finish();

                        }
                        else if(!get_ans.getText().toString().equals(option_Ans) && get_ans != null)
                        {

                            AlertDialog.Builder builder = new AlertDialog.Builder(page_5.this);
                            builder.setTitle("The correct answer : ").setMessage(option_Ans)
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            startActivity(intent);
                                            finish();

                                        }
                                    }).setCancelable(false);

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();

                        }
                        if(get_ans == null)
                        {
                            Toast.makeText(page_5.this,"select your answer",Toast.LENGTH_SHORT).show();
                        }

                    }
                }
        );
    }
}

