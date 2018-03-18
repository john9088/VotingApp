package com.example.jason12.votingapp;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends ActionBarActivity {

    EditText e1,e2,e3,e4;
    Button b1,b2;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        e1 = (EditText)findViewById(R.id.e1);
        e2 = (EditText)findViewById(R.id.e2);
        e3 = (EditText)findViewById(R.id.e3);
        e4 = (EditText)findViewById(R.id.e4);

        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        db = new DataBase(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pass,pid;
                String name = e1.getText().toString();
                String Class = e3.getText().toString();
                String div = e4.getText().toString();

                Random random = new Random();
                if(e1.getText().toString().equals("") || e2.getText().toString().equals("") ||e3.getText().toString().equals("") ||e4.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this, "Enter all fields...", Toast.LENGTH_SHORT).show();
                }
                else {
                    pass = random.nextInt(10000);
                    pid = Integer.parseInt(e2.getText().toString());
                    Toast.makeText(MainActivity.this, "Your Password is: " + pass, Toast.LENGTH_SHORT).show();
                    boolean input = db.insertData(name, pid, pass, Class, div);
                    if(input == true){
                        Toast.makeText(MainActivity.this, "INSERTED", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(MainActivity.this, "NOT INSERTED", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainactivity = new Intent(MainActivity.this,Login.class);
                startActivity(mainactivity);
                finish();
            }
        });
    }
}
