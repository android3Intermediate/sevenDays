package com.example.isaac.sqlite;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter {
    List list=new ArrayList();

    public Adapter(@NonNull Context context, int resource) {

        super(context, resource);

    }
    static class LayoutHandler
    {

        TextView NAME;
        TextView AGE;

    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
        list.add(object);
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
    public View getView(int position,View convertView, ViewGroup parent) {

        View row=convertView;
        LayoutHandler layoutHandler;

        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.data_layout,parent,false);
            layoutHandler=new LayoutHandler();
            layoutHandler.NAME= row.findViewById(R.id.name);
            layoutHandler.AGE=row.findViewById(R.id.age);
            row.setTag(layoutHandler);


        }
        else
        {
            layoutHandler=(LayoutHandler)row.getTag();
        }

        DataStructure myDataStructure=(DataStructure)this.getItem(position);
        layoutHandler.NAME.setText(myDataStructure.getName());
        layoutHandler.AGE.setText(myDataStructure.getAge());
        return row;

    }




}
