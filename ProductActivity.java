package com.example.meher.Inventory;

/**
 * Created by Meher Khan on 01/08/2017.
 */




import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProductActivity extends AppCompatActivity {

    ImageView ivProductImage;

    Button btnDef;

    EditText edtId;
    EditText edtName;
    EditText edtDescription;
    EditText edtQuantity;
    EditText edtPrice;
    String strImgPath = "";

    final int REQUEST_CODE_GALLERY = 999;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        init();

        SharedPreferences prefs = getSharedPreferences("FoodSqliteDemo", MODE_PRIVATE);
        int id = prefs.getInt("LASTNUMBER", 1);
        edtId.setText(String.valueOf(id));

        edtName.setFocusable(true);

        ivProductImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
                     }
        });

        btnDef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( edtName.getText().toString().matches("") ) {
                    Toast.makeText(ProductActivity.this, "Please enter the name!", Toast.LENGTH_SHORT).show();
                }
                else if ( edtQuantity.getText().toString().matches("") ) {
                    Toast.makeText(ProductActivity.this, "Please enter the quantity!", Toast.LENGTH_SHORT).show();
                }
                else if ( edtPrice.getText().toString().matches("") ) {
                    Toast.makeText(ProductActivity.this, "Please enter the price!", Toast.LENGTH_SHORT).show();
                }
                else if ( edtDescription.getText().toString().matches("") ) {
                    Toast.makeText(ProductActivity.this, "Please enter the price!", Toast.LENGTH_SHORT).show();
                }
                else if ( strImgPath.matches("") ) {
                    Toast.makeText(ProductActivity.this, "Please select the image!", Toast.LENGTH_SHORT).show();
                }
                else {
                    ProductMainActivity.sqLiteHelper.insertData(
                            edtName.getText().toString().trim(),
                            edtDescription.getText().toString().trim(),
                            edtQuantity.getText().toString().trim(),
                            edtPrice.getText().toString().trim(),
                            imageViewToByte(ivProductImage),
                            strImgPath);

                    SharedPreferences prefs = getSharedPreferences("FoodSqliteDemo", MODE_PRIVATE);
                    int id = prefs.getInt("LASTNUMBER", 1);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putInt("LASTNUMBER", id + 1);
                    editor.commit();

                    edtId.setText(String.valueOf(id + 1));
                    edtName.setText("");
                    edtDescription.setText("");
                    edtQuantity.setText("");
                    edtPrice.setText("");
                    ivProductImage.setImageBitmap(null);
                    strImgPath = "";

                    Toast.makeText(getApplicationContext(), "Added successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void init() {
        edtId = (EditText) findViewById(R.id.edtId);
        edtName = (EditText) findViewById(R.id.edtName);
        edtDescription = (EditText) findViewById(R.id.edtDescription);
        edtQuantity = (EditText) findViewById(R.id.edtQuantity);
        edtPrice = (EditText) findViewById(R.id.edtPrice);

        ivProductImage = (ImageView) findViewById(R.id.ivProductImage);

        btnDef = (Button) findViewById(R.id.btnDefault);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(requestCode == REQUEST_CODE_GALLERY){
            if(grantResults.length >0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_GALLERY);
            }
            else {
                Toast.makeText(getApplicationContext(), "You don't have permission to access file location!", Toast.LENGTH_SHORT).show();
            }
            return;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null){

            Uri uri;
            uri= data.getData();
            uri.getPath();

            String w = uri.toString();

            strImgPath = convertMediaUriToPath(uri);

            //   Toast.makeText(ProductActivity.this, strImgPath, Toast.LENGTH_LONG).show();

            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);

                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                ivProductImage.setImageBitmap(bitmap);

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

    private byte[] imageViewToByte(ImageView image) {
        Bitmap bitmap = ((BitmapDrawable)image.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }
}
