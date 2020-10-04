package com.example.jason12.votingapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
    TextView t3, tx;
    Button b1, b2;
    EditText e1,e2;
    DataBase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        t3 = (TextView) findViewById(R.id.t3);
        tx = (TextView) findViewById(R.id.tx);
        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        e1 = (EditText)findViewById(R.id.e1);
        e2 = (EditText)findViewById(R.id.e2);
        db = new DataBase(this);

        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainactivity = new Intent(Login.this, MainActivity.class);
                startActivity(mainactivity);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent admin = new Intent(Login.this, admin.class);
                startActivity(admin);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = e1.getText().toString();
                String pass = e2.getText().toString();
                boolean flag = false;
                Cursor res = db.getdata();
                if (res.getCount() == 0)
                    Toast.makeText(Login.this, "EMPTY", Toast.LENGTH_SHORT).show();
                else {
                    while (res.moveToNext()) {
                        if (id.equals(res.getString(1)) && pass.equals(res.getString(2))) {
                            flag = true;
                            Bundle b = new Bundle();
                            b.putString("PID",id);
                            Intent vote = new Intent(Login.this,Vote.class);
                            vote.putExtras(b);
                            startActivity(vote);
                            finish();
                            break;
                        }else
                            flag = false;
                    }
                    if(flag == true)
                        Toast.makeText(Login.this, "SUCCESS...", Toast.LENGTH_SHORT).show();
                    else
                        Toast.makeText(Login.this, "Wrong Password or ID.", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }
}

