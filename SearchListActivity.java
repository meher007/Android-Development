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
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.GridView;
        import android.widget.Toast;

        import java.io.FileNotFoundException;
        import java.io.InputStream;
        import java.util.ArrayList;


public class SearchListActivity extends AppCompatActivity {
    GridView gvProductList;

    ProductListAdapter plaProductAdapter;
    ArrayList<Product> alProductList = new ArrayList<Product>();

    Button btnSearch;
    EditText edtId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_list);

        init();

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtId.getText().toString().matches("")) {
                    Toast.makeText(SearchListActivity.this, "Please enter the id!", Toast.LENGTH_SHORT).show();
                }
                else {
                    alProductList = ProductMainActivity.sqLiteHelper.getProductsList(Integer.parseInt(edtId.getText().toString()));
                    plaProductAdapter = new ProductListAdapter(alProductList, SearchListActivity.this, true);
                    gvProductList.setAdapter(plaProductAdapter);
                    if ( alProductList.size() == 0 ) {
                        Toast.makeText(SearchListActivity.this, "No products match your search!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        gvProductList = (GridView) findViewById(R.id.gvSearchList);

    }

    private void init() {
        edtId = (EditText) findViewById(R.id.edtSearchId);
        btnSearch = (Button) findViewById(R.id.btnSearchDef);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 9999 && resultCode == RESULT_OK && data != null){

            Uri uri;
            uri= data.getData();
            uri.getPath();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                plaProductAdapter.setProductImage(bitmap);
                plaProductAdapter.setImagePath(convertMediaUriToPath(uri));

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
