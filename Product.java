package com.example.meher.Inventory;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by Meher Khan on 27/07/2017.
 */

public class Product extends Activity {



        int id;
        String name;
        String description;
        String quantity;
        String price;
        String path;
        byte[] image;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product);  /**this display.xml file, it is setting the contentview of display.xml*/


    }
}