package com.sleepingpandaaa.bankingsystem;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private String TABLE_NAME = "users_table";
    private String TABLE_NAME1 = "transfers_table";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "User.db", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME +" (PHONENUMBER INTEGER PRIMARY KEY ,NAME TEXT,BALANCE DECIMAL,EMAIL VARCHAR,ACCOUNT_NO VARCHAR,IFSC_CODE VARCHAR)");
        db.execSQL("create table " + TABLE_NAME1 +" (TRANSACTIONID INTEGER PRIMARY KEY AUTOINCREMENT,DATE TEXT,FROMNAME TEXT,TONAME TEXT,AMOUNT DECIMAL,STATUS TEXT)");
        db.execSQL("insert into users_table values(9789235000,'Karthiga',10000.00,'karthyraj@gmail.com','XXXXXXXXXXXX5918','IDIB000B987')");
        db.execSQL("insert into users_table values(9865451111,'Mugenrao',25000.67,'nadhiyamugen@gmail.com','XXXXXXXXXXXX1711','IDIB000B987')");
        db.execSQL("insert into users_table values(7502822222,'Vasugi',5000.56,'vasuvd@gmail.com','XXXXXXXXXXXX2643','IDIB000B987')");
        db.execSQL("insert into users_table values(9942533333,'Udhay',1500.01,'udhaya123@gmail.com','XXXXXXXXXXXX4111','IDIB000B987')");
        db.execSQL("insert into users_table values(9442258285,'Ravi',2500.48,'ravismra@gmail.com','XXXXXXXXXXXX2346','IDIB000B987')");
        db.execSQL("insert into users_table values(9976414100,'Harish',68000.16,'harish@gmail.com','XXXXXXXXXXXX3562','IDIB000B987')");
        db.execSQL("insert into users_table values(8903644444,'Suprathik',5900.00,'srithik@gmail.com','XXXXXXXXXXXX4876','IDIB000B987')");
        db.execSQL("insert into users_table values(9751488888,'Reenasri',990.22,'reenu@gmail.com','XXXXXXXXXXXX5278','IDIB000B987')");
        db.execSQL("insert into users_table values(9878685848,'Sisira',800.46,'sisira@gmail.com','XXXXXXXXXXXX3498','IDIB000B987')");
        db.execSQL("insert into users_table values(9998887776,'Virushka',7890.90,'viratanushka@gmail.com','XXXXXXXXXXXX4193','IDIB000B987')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME1);
        onCreate(db);
    }

    public Cursor readalldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users_table", null);
        return cursor;
    }

    public Cursor readparticulardata(String phonenumber){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public Cursor readselectuserdata(String phonenumber) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from users_table except select * from users_table where phonenumber = " +phonenumber, null);
        return cursor;
    }

    public void updateAmount(String phonenumber, String amount){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update users_table set balance = " + amount + " where phonenumber = " +phonenumber);
    }

    public Cursor readtransferdata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from transfers_table", null);
        return cursor;
    }

    public boolean insertTransferData(String date,String from_name, String to_name, String amount, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("DATE", date);
        contentValues.put("FROMNAME", from_name);
        contentValues.put("TONAME", to_name);
        contentValues.put("AMOUNT", amount);
        contentValues.put("STATUS", status);
        Long result = db.insert(TABLE_NAME1, null, contentValues);
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }
}
