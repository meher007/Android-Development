package com.example.meher.Inventory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by home on 24/07/2017.
 */

public class SignUp extends Activity {


    DatabaseHelper helper;
    EditText edtname, edtemail, edtuname, edtpass1, edtpass2, edttest;
    Button btAddData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);


        //String username= getIntent().getStringExtra("Username");
        //TextView tv = (TextView)findViewById(R.id.TVusername);
        //tv.setText(username);


        helper = new DatabaseHelper(this);

        edtname= (EditText) findViewById(R.id.TFname);
        edtemail= (EditText) findViewById(R.id.TFemail);
        edtuname= (EditText) findViewById(R.id.TFuname);
        edtpass1= (EditText) findViewById(R.id.TFpass1);
        edtpass2= (EditText) findViewById(R.id.TFpass2);

        //edttest=(EditText)  findViewById(R.id.my);

        btAddData = (Button)findViewById(R.id.Bsignupbutton);




        AddData();

    }


    public  void AddData() {



        btAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String pass1str = edtpass1.getText().toString();/** converting to string, get the text from Edittext object*/
                        String pass2str = edtpass2.getText().toString();
                        /** if it's int then we can write pass1==pass2 but it's in string we can't */
                        //if (edtpass1.equals(edtpass2))
                        if (pass1str.equals(pass2str)) {

                            boolean isInserted = helper.insertdata(null, edtname.getText().toString(),
                                    edtemail.getText().toString(),
                                    edtuname.getText().toString(),
                                    edtpass1.getText().toString());
                            if (isInserted == true) {
                                Toast.makeText(SignUp.this, "Data Inserted", Toast.LENGTH_LONG).show();
                                String str = edtuname.getText().toString();
                                Intent i = new Intent(SignUp.this, PageViewMain.class);
                                i.putExtra("Username", str);
                                startActivity(i);

                                edtname.setText("");
                                edtemail.setText("");
                                edtuname.setText("");
                                edtpass1.setText("");
                                edtpass2.setText("");


                            } else
                                Toast.makeText(SignUp.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                        }


                        else
                        {

                            Toast.makeText(SignUp.this, "Password don't match", Toast.LENGTH_LONG).show();
                        }
                    }

                    }


        );

    }


    }












