package com.example.meher.Inventory;

/**
 * Created by home on 06/08/2017.
 */





import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Quoc Nguyen on 13-Dec-16.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String TAG = "SQLiteHelper";

    public SQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void insertData(String name, String description, String quantity, String price, byte[] image, String path){
        SQLiteDatabase database = getWritableDatabase();

        String sql = "INSERT INTO FOOD VALUES (NULL, ?, ?, ?, ?, ?, ?)";

        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindString(2, description);
        statement.bindString(3, quantity);
        statement.bindString(4, price);
        statement.bindBlob(5, image);
        statement.bindString(6, path);

        statement.executeInsert();
    }

    public Cursor getData(String sql){
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS FOOD(Id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, description VARCHAR, quantity VARCHAR, price VARCHAR, image BLOB, path VARCHAR)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.w(TAG, "Upgrading database from version " + i + " to "
                + i1 + ", which will destroy all old data");

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS FOOD");;

        onCreate(sqLiteDatabase);
    }

    public ArrayList<Product> getAllProductsList() {
        Cursor data = getData("SELECT * FROM FOOD");

        ArrayList<Product> array = new ArrayList<Product>();
        for(int i = 0; i < data.getCount(); i ++)
        {
            Product obj = new Product();
            data.moveToPosition(i);

            obj.id = data.getInt(0);
            obj.name = data.getString(1);
            obj.description = data.getString(2);
            obj.quantity = data.getString(3);
            obj.price = data.getString(4);
            obj.image = data.getBlob(5);
            obj.path = data.getString(6);

            array.add(obj);
        }
        return array;
    }

    public ArrayList<Product> getProductsList(int id) {
        Cursor data = getData("SELECT * FROM FOOD WHERE Id = " + id);

        ArrayList<Product> array = new ArrayList<Product>();
        for(int i = 0; i < data.getCount(); i ++)
        {
            Product obj = new Product();
            data.moveToPosition(i);

            obj.id = data.getInt(0);
            obj.name = data.getString(1);
            obj.description = data.getString(2);
            obj.quantity = data.getString(3);
            obj.price = data.getString(4);
            obj.image = data.getBlob(5);
            obj.path = data.getString(6);

            array.add(obj);
        }
        return array;
    }

    public void deleteProduct(int id) {
        SQLiteDatabase database = getWritableDatabase();
        database.delete("FOOD", "Id = " + id, null);
    }

    public void updateProduct(int id, String name, String description, String quantity, String price, byte[] image, String path) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("description", description);
        cv.put("quantity", quantity);
        cv.put("price", price);
        cv.put("image", image);
        cv.put("path", path);
        database.update("FOOD", cv, "Id = " + id, null);
    }
}