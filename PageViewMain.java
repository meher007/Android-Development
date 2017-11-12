package com.example.meher.Inventory;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import adapters.ViewListAdapter;
import entities.ViewCad;


public class PageViewMain extends Activity {

    private GridView gridViewPage;
    private List<ViewCad> graphicalview = new ArrayList<ViewCad>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpage);
        String username= getIntent().getStringExtra("Username");
        TextView tv = (TextView)findViewById(R.id.TVusername);
        tv.setText(username);


        graphicalview.add(new ViewCad("p01", " ",R.drawable.customer));
        graphicalview.add(new ViewCad("p02", " ",R.drawable.supplier));
        graphicalview.add(new ViewCad("p03", " ",R.drawable.purchase));
        graphicalview.add(new ViewCad("p04", " ", R.drawable.sales));
        graphicalview.add(new ViewCad("p05", " ",R.drawable.productlist));
        graphicalview.add(new ViewCad("p06", " ",R.drawable.stockonhand));
        graphicalview.add(new ViewCad("p03", " ",R.drawable.purchaseorder));
        graphicalview.add(new ViewCad("p04", " ",R.drawable.salesorder));
        graphicalview.add(new ViewCad("p05", " ",R.drawable.expenseslist));
        graphicalview.add(new ViewCad("p06", " ",R.drawable.profitlost));



        this.gridViewPage = (GridView) findViewById(R.id.view_PageId);
        this.gridViewPage.setAdapter(new ViewListAdapter(this, graphicalview));

        this.gridViewPage.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                ViewCad imageview = graphicalview.get(i);
                if (imageview.getId()== "p03"){

                    Intent intent = new Intent(PageViewMain.this, ProductMainActivity.class);
                    startActivity(intent);
                }

                if (imageview.getId()== "p05"){

                    Intent intent = new Intent(PageViewMain.this, ProductListActivity.class);
                    startActivity(intent);
                }

            }
        });
    }
}
