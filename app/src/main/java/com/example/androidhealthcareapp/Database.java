package com.example.androidhealthcareapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Database extends SQLiteOpenHelper {

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry1="create table users(Username text,Email text,Password text)";
       sqLiteDatabase.execSQL(qry1);

        String qry2="create table cart(Username text,product text,price float ,otype text)";
        sqLiteDatabase.execSQL(qry2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
    }
    public void Register(String Username,String Email,String Password){
        ContentValues cv=new ContentValues();
        cv.put("Username",Username);
        cv.put("Email",Email);
        cv.put("Password",Password);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("Users",null,cv);
        db.close();
    }
    public int Login(String Username,String Password){
        int result=0;
        String str[]=new String[2];
        str[0]=Username;
        str[1]=Password;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from users where Username=? and password=?",str);
        if(c.moveToFirst()){
            result=1;
        }
        return result;
    }
    public void addCart(String Username,String product,float price,String otype){
        ContentValues cv=new ContentValues();
        cv.put("Username",Username);
        cv.put("product",product);
        cv.put("price",price);
        cv.put("otype",otype);
        SQLiteDatabase db=getWritableDatabase();
        db.insert("cart",null,cv);
        db.close();
    }
    public int checkCart(String Username,String product){
        int result=0;
        String str[]=new String[2];
        str[0]=Username;
        str[1]=product;
        SQLiteDatabase db=getReadableDatabase();
        Cursor c=db.rawQuery("select * from users where Username=? and product=?",str);
        if(c.moveToFirst()){
            result=1;
        }
        db.close();
        return result;
    }
    public void removeCart(String Username,String otype){
        String str[]=new String[2];
        str[0]=Username;
        str[1]=otype;
        SQLiteDatabase db=getWritableDatabase();
        db.delete("cart","Username=? and otype=?",str);
        db.close();
    }
}
