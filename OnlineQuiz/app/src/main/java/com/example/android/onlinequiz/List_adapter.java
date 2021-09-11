package com.example.android.onlinequiz;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class List_adapter extends ArrayAdapter {

    List list = new ArrayList();
    List_adapter(Context context, int resouce)
    {
        super(context,resouce);

    }



    static class LayoutHandler
    {
        TextView Name, Score;
    }

    class Sort_By_Score implements Comparator<Data_info_store>
    {
        // Used for sorting in ascending order of
        // roll number
        public int compare(Data_info_store a, Data_info_store b)
        {
            return  b.getScore()- a.getScore();
        }
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
        Collections.sort(list, new Sort_By_Score());
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View row = convertView;
        LayoutHandler layoutHandler;
        if(row == null)
        {
            LayoutInflater layoutInflater = (LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = layoutInflater.inflate(R.layout.row,parent,false);
            layoutHandler = new LayoutHandler();
            layoutHandler.Name = (TextView)row.findViewById(R.id.Name);
            layoutHandler.Score = (TextView)row.findViewById(R.id.Score);
            row.setTag(layoutHandler);
        }
        else
        {
            layoutHandler = (LayoutHandler)row.getTag();
        }
        Data_info_store data_info_store = (Data_info_store)this.getItem(position);
        layoutHandler.Name.setText(data_info_store.getName());
        layoutHandler.Score.setText(Integer.toString(data_info_store.getScore()));

        return row;
    }
}
