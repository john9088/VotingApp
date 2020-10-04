package com.example.jason12.votingapp;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class admin extends AppCompatActivity {
    EditText e1,e2;
    TextView t1;
    Button b1,b2;
    DataBase mydb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        e1 = (EditText)findViewById(R.id.e1);
        e2 = (EditText)findViewById(R.id.e2);
        t1 = (TextView)findViewById(R.id.t1);
        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        mydb = new DataBase(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = e1.getText().toString();
                String pass = e2.getText().toString();

                if(id.equals("admin") && pass.equals("123")){
                    Cursor res = mydb.getdata();
                    if(res.getCount() == 0)
                        message("ERROR","EMPTY");

                    StringBuffer buff = new StringBuffer();
                    while(res.moveToNext()){
                        buff.append("Name: "+ res.getString(0)+"\n");
                        buff.append("PID: "+ res.getString(1)+"\n");
                        buff.append("Password: "+ res.getString(2)+"\n");
                        buff.append("Class: "+ res.getString(3)+"\n");
                        buff.append("Div: "+ res.getString(4)+"\n\n");
                        buff.append("Vote: "+ res.getString(5)+"\n\n");
                    }
                    //show data
                    message("DATA",buff.toString());
                }
                else{
                    Toast.makeText(admin.this, "Wrong Credential Entered", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = mydb.getVoteCount();
                if(res.getCount() == 0)
                    message("ERROR","EMPTY");

                StringBuffer buff = new StringBuffer();
                while(res.moveToNext()){
                    buff.append("Vote: "+ res.getString(0)+"\n");
                    buff.append("Count: "+ res.getString(1)+"\n\n");
                }

                //show data
                message("DATA",buff.toString());
            }
        });
    }
    public void message(String title,String msg){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(msg);
        builder.show();
    }
}
