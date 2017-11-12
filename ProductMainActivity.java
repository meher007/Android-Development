package com.example.meher.Inventory;

/**
 * Created by home on 06/08/2017.
 */




        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;

public class ProductMainActivity extends AppCompatActivity {

    Button btnAdd;
    Button btnSearch;
    Button btnView;

    public static SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_product);

        init();

        sqLiteHelper = new SQLiteHelper(this, "FoodDB.sqlite", null, 1);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProductMainActivity.this, ProductActivity.class));
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductMainActivity.this, SearchListActivity.class));
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProductMainActivity.this, ProductListActivity.class));
            }
        });
    }

    private void init(){
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        btnView = (Button) findViewById(R.id.btnView);
    }


}