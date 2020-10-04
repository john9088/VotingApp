package com.example.jason12.votingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



/**
 * Created by jason12 on 2/18/2018.
 */

public class DataBase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "VoteNow.db";
    public static final String TABLE1_NAME = "CMPN";
    public static final String COL11 = "Name";
    public static final String COL12 = "PID";
    public static final String COL13 = "Password";
    public static final String COL14 = "Class";
    public static final String COL15 = "Div";
    public static final String COL16 = "Vote";
    SQLiteDatabase db;


    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE1_NAME +"(Name TEXT,PID INTEGER primary key,Password INTEGER,Class TEXT,DIV TEXT,Vote INTEGER);");

    }


    public boolean enterVote(String pid,int vote) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL16,vote);
        long result = db.update(TABLE1_NAME,contentValues,COL12 + " = ?" ,new String[]{pid});
        if(result == -1)
            return false;
        else
            return true;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE1_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, int pid, int password, String Class, String div){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL11,name);
        contentValues.put(COL12,pid);
        contentValues.put(COL13,password);
        contentValues.put(COL14,Class);
        contentValues.put(COL15,div);

        long result = db.insert(TABLE1_NAME,null,contentValues);

        if(result == -1)
            return false;
        else
            return true;
    }

    public Cursor getdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+ TABLE1_NAME,null);
        return res;
    }

    public Cursor getVoteCount(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select Vote,count(vote) from "+ TABLE1_NAME +" GROUP BY Vote",null);

        return res;
    }


}
