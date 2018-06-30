package com.example.isaac.sqlite;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Display extends AppCompatActivity {
    ListView listView;
    SQLiteDatabase sqLiteDatabase;
    helper myHelper;
    Cursor cursor;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

      DisplayDataTask displayDataTask=new DisplayDataTask();
      displayDataTask.execute();


    }

    public class DisplayDataTask extends AsyncTask<Cursor,Void,DataStructure>
    {
        @Override
        protected DataStructure doInBackground(Cursor... cursors) {

            listView=(ListView)findViewById(R.id.listView);
            myHelper=new helper(getApplicationContext());
            sqLiteDatabase=myHelper.getReadableDatabase();
            cursor=myHelper.getInformation(sqLiteDatabase);
            adapter=new Adapter(getApplicationContext(),R.layout.data_layout);
            listView.setAdapter(adapter);

            if(cursor.moveToFirst())
            {
                do {

                    String name;
                    String age;
                    name=cursor.getString(0);
                    age=cursor.getString(1);
                    DataStructure dataStructure=new DataStructure(name,age);
                    adapter.add(dataStructure);

                }while(cursor.moveToNext());

            }
            return null;
        }
    }


}
