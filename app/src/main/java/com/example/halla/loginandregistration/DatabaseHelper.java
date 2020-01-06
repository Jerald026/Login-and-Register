package com.example.halla.loginandregistration;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public DatabaseHelper(Context context) {
        super(context, "Login.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table user(email text primary key, password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("frop table if exists user");
    }

    //inserting in database
    public boolean insert(String email, String pass) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", pass);
        long ins = db.insert("user", null, contentValues);

        if (ins == 1) return false;
        else return true;
    }

    //checking if email exists
    public Boolean chkEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where email = ?", new String[]{email});

        if (cursor.getCount() > 0) return false;
        else return true;
    }

    //checking the email and password
    public Boolean emailPass(String email, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE email=? AND password=?", new String[]{email,pass});

        if (cursor.getCount() > 0) return true;
        else return false;
    }
}
