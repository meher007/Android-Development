package com.example.meher.Inventory;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Meher Khan on 25/07/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION =1;
    private static final String DATABASE_NAME = "inventory20.db";
    public static final String TABLE_NAME = "contacts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_UNAME = "uname";
    public static final String COLUMN_PASS = "pass";
    SQLiteDatabase db;

    private static final String TABLE_CREATE="create table contacts (id interger primary key," +
            "name text not null, email text not null,uname text not null, pass text not null);";

    public DatabaseHelper(Context context) {   // databse is created by calling constrator
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(TABLE_CREATE);
        this.db=db;
    }


    public boolean insertdata(String id,String name,String email,String uname,String pass)
    {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(COLUMN_ID,id);
        contentValues.put(COLUMN_NAME,name);
        contentValues.put(COLUMN_EMAIL,email);
        contentValues.put(COLUMN_UNAME,uname);
        contentValues.put(COLUMN_PASS, pass);
        long result = db.insert(TABLE_NAME,null ,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }





    public String searchPass(String uname)
    {
        db = this.getReadableDatabase(); //we are reading database, not inserting
        String query ="select uname, pass from " + TABLE_NAME;
        Cursor cursor=db.rawQuery(query, null);
        String a,b;

        b="not found";

        if(cursor.moveToFirst())
        {

            do {
                a=cursor.getString(0);
                if (a.equals(uname))
                {
                    b=cursor.getString(1);
                    break;
                }

            }
            while (cursor.moveToNext());

        }
        return b;


    }




    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query ="DROP TABLE IF EXISTS"+ TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);

    }
}
