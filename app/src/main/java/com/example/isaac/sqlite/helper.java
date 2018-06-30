package com.example.isaac.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class helper extends SQLiteOpenHelper{

    private static final String DATABASE_NAME="MYDATABASE.DB";
    private static final int DATABASE_VERSION=1;
    private static final String CREATE_QUERY="CREATE TABLE "
    + contract.addInformation.TABLE_NAME +
    "(" + contract.addInformation.NAME +
    " TEXT, " + contract.addInformation.AGE + " TEXT )";

    private static final String DELETE_TABLE="DROP TABLE IF EXISTS "+ contract.addInformation.TABLE_NAME;

     public helper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        android.util.Log.e("Database Operations", "Database Created/opened....");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);

        {
            android.util.Log.e("Database Operations", "Database Created/opened....");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_TABLE);
    }



    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }

    public static void addDataToDatabase(String name,String age,SQLiteDatabase sqLiteDatabase)
    {
        ContentValues contentValues=new ContentValues();
        contentValues.put(contract.addInformation.NAME,name);
        contentValues.put(contract.addInformation.AGE,age);
        sqLiteDatabase.insert(contract.addInformation.TABLE_NAME,null,contentValues);
         android.util.Log.e("Database Operations", "Database Created/One row inserted....");
    }

    public Cursor getInformation(SQLiteDatabase db)
    {
        Cursor cursor;
        String projections[]={contract.addInformation.NAME,contract.addInformation.AGE};
        cursor=db.query(contract.addInformation.TABLE_NAME,projections,null,null,null,null,null);
        return cursor;

    }
}
