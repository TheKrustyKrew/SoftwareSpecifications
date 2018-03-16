package com.example.android.softwarespecifications;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rache on 3/14/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "SoftwareSpecification.db";
    private static final String TABLE_NAME = "Login Info";
    private static final String COLUMN_NAME = "Name";
    private static final String COLUMN_USERNAME = "Username";
    private static final String COLUMN_PASSWORD = "Password";

    SQLiteDatabase db;
    private static final String TABLE_CREATE = "create table Login Info (Username text primary key not null " +
            "auto_increment , Password text not null, Name text not null);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
        this.db = db;
    }

    public void insertContact(Contact c){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, c.getUsername());
        values.put(COLUMN_PASSWORD, c.getPassword());
        values.put(COLUMN_NAME, c.getName());

        db.insert(TABLE_NAME, null, values);
    }

    public String searchPass(String uname){
        db = this.getReadableDatabase();
        String query = "select uname, Password from" + TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);

        String a,b;
        b = "Not Found";
        if(cursor.moveToFirst()){
            do {
                a = cursor.getString(0);

                if(a.equals(uname)){
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        this.onCreate(db);
    }
}
