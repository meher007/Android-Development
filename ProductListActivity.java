package com.example.meher.Inventory;

/**
 * Created by home on 06/08/2017.
 */



        import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;


public class ProductListActivity extends AppCompatActivity {
    GridView gvProductList;
    ProductListAdapter plaProductAdapter;
    ArrayList<Product> alProductList;
    SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        alProductList = ProductMainActivity.sqLiteHelper.getAllProductsList();
        plaProductAdapter = new ProductListAdapter(alProductList, this, false);
        gvProductList = (GridView) findViewById(R.id.gvProductList);
        gvProductList.setAdapter(plaProductAdapter);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 9999 && resultCode == RESULT_OK && data != null){

            Uri uri;
            uri= data.getData();
            uri.getPath();

            String path = convertMediaUriToPath(uri);

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                plaProductAdapter.setProductImage(bitmap);
                plaProductAdapter.setImagePath(path);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        super.onActivityResult(requestCode, resultCode, data);

    }

    protected String convertMediaUriToPath(Uri uri1) {
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri1, proj, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String path = cursor.getString(column_index);
        cursor.close();

        return path;
    }
}
