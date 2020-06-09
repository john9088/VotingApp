package com.example.jason12.votingapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Vote extends AppCompatActivity {
    Button b1,b2;
    RadioGroup rg1;
    RadioButton rb1,rb2,rb3,rb4,rb5;
    DataBase db;
    String id;
    int vote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        b1 = (Button)findViewById(R.id.b1);
        b2 = (Button)findViewById(R.id.b2);
        rg1 = (RadioGroup)findViewById(R.id.rg1);
        rb1 = (RadioButton)findViewById(R.id.rb1);
        rb2 = (RadioButton)findViewById(R.id.rb2);
        rb3 = (RadioButton)findViewById(R.id.rb3);
        rb4 = (RadioButton)findViewById(R.id.rb4);
        rb5 = (RadioButton)findViewById(R.id.rb5);
        db = new DataBase(this);


        Bundle b = getIntent().getExtras();
        id = b.getString("PID");
        //Toast.makeText(this, id, Toast.LENGTH_SHORT).show();

        rg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {

                switch(checkedId){
                    case R.id.rb1:
                        vote = Integer.parseInt(rb1.getText().toString());
                        break;
                    case R.id.rb2:
                        vote = Integer.parseInt(rb2.getText().toString());
                        break;
                    case R.id.rb3:
                        vote = Integer.parseInt(rb3.getText().toString());
                        break;
                    case R.id.rb4:
                        vote = Integer.parseInt(rb4.getText().toString());
                        break;
                    case R.id.rb5:
                        vote = Integer.parseInt(rb5.getText().toString());
                        break;
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE:
                                boolean input = db.enterVote(id,vote);
                                if(input == true){
                                    Toast.makeText(Vote.this, "Thank-You", Toast.LENGTH_SHORT).show();
                                }
                                else
                                    Toast.makeText(Vote.this, "Try Again", Toast.LENGTH_SHORT).show();

                                Intent exit = new Intent(Vote.this,Exit.class);
                                startActivity(exit);
                                finish();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                Toast.makeText(Vote.this, "ENTER VALID VOTE", Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(Vote.this);
                builder.setMessage("Are you sure about your vote?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(Vote.this, Login.class);
                startActivity(login);
            }
        });


    }
}
