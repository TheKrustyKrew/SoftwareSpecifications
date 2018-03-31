package com.example.android.softwarespecifications;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by rache on 3/31/2018.
 */

public class DataRetrival {
    private static final String TABLE_NAME = "Journal Info";
    private static final String TABLE_CREATE = "create table Journal Info ("

    public Bitmap getImage(Integer id){
        Bitmap bt = null;
        Cursor cursor = db.rawQuery("select * from images where id=?", new String[]{String.valueOf(id)});
        if(cursor.moveToNext()){
            byte[] imag = cursor.getBlob(1);
            bt = BitmapFactory.decodeByteArray(imag, 0, imag.length);
        }
        return bt;
    }

    public void insertJournalEntry(){

    }

    public Cursor getInformation(SQLiteDatabase db){
        String[] projection = {};
    }
}
