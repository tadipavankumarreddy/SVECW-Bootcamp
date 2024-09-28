package com.svecw.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {

    public static String DB_NAME = "svecw.db";
    public static int DB_VERSION = 1;

    public DBhelper(@Nullable Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_query = "create table students(id integer primary key autoincrement, name text, age integer);";
        db.execSQL(create_query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(ContentValues cv){
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("students",null,cv);
    }

    public Cursor readData(){
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("select * from students", null);
    }
}
