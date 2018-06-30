package com.example.isaac.sqlite;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText mName;
    EditText age;
    Button sendButton;
    SQLiteDatabase sqLiteDatabase;
    helper myHelper;
    Button viewData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mName=(EditText) findViewById(R.id.name);
        age=(EditText)findViewById(R.id.age);
        sendButton=(Button)findViewById(R.id.sendButton);
        viewData=(Button)findViewById(R.id.viewDataList);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData(v);

            }
        });

        viewData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDataList(v);
            }
        });

    }

    public void addData(View view)
    {
      AddDataTask addDataTask=new AddDataTask();
      addDataTask.execute();
    }

    public void openDataList(View view)
    {
        Intent intent=new Intent(this,Display.class);
        startActivity(intent);
    }

    public class AddDataTask extends AsyncTask<View,Void,DataStructure>
    {
        @Override
        protected DataStructure doInBackground(View... views) {
            String name=mName.getText().toString();
            String myAge=age.getText().toString();
            myHelper=new helper(getApplicationContext());
            sqLiteDatabase=myHelper.getWritableDatabase();
            myHelper.addDataToDatabase(name,myAge,sqLiteDatabase);


            return null;
        }

        @Override
        protected void onPostExecute(DataStructure dataStructure) {
            Toast.makeText(getApplicationContext(),"data added",Toast.LENGTH_LONG).show();
            myHelper.close();
        }
    }



}
