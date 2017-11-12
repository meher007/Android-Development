package com.example.meher.Inventory;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);


    EditText a;
    EditText b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void onButtonClick(View v)
    {
        a =(EditText) findViewById(R.id.TFusername);
        String str = a.getText().toString();
        b =(EditText) findViewById(R.id.TFpassword);
        String pass = b.getText().toString();
        String password = helper.searchPass(str);

        if (!(str.length()==0)) {

            if (v.getId() == R.id.Blogin)

            {
                if (pass.equals(password))

                {

                    Intent i = new Intent(MainActivity.this, PageViewMain.class);
                    //i.putExtra("Username", str); /** passing information */
                    i.putExtra("Username", str);
                    startActivity(i);
                    a.setText("");
                    b.setText("");
                } else {
                    Toast temp = Toast.makeText(MainActivity.this, "Username and Password don't match!", Toast.LENGTH_SHORT); //the message will be mainactivity thta's why MainActivity.this
                    temp.show();

                }
            }
            else {
                Toast temp = Toast.makeText(MainActivity.this, "Username and Password are empty!", Toast.LENGTH_SHORT); //the message will be mainactivity thta's why MainActivity.this
                temp.show();
            }

        }

        if (v.getId()==R.id.Bsignup)
        {
            Intent i= new Intent(MainActivity.this, SignUp.class);

            startActivity(i);

            a.setText("");
            b.setText("");

        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
