package com.example.android.softwarespecifications;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by rache on 3/31/2018.
 */

public class DataRetrival {
    public Bitmap getImage(Integer id){
        SQLiteDatabase db = this.getWritableDatabase();
        Bitmap bt = null;
        Cursor cursor = db.rawQuery("select * from images where id=?", new String[]{String.valueOf(id)});
        if(cursor.moveToNext()){
            byte[] imag = cursor.getBlob(1);
            bt = BitmapFactory.decodeByteArray(imag, 0, imag.length);
        }
        return bt;
    }
}
