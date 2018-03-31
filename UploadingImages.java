package com.example.android.softwarespecifications;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;

/**
 * Created by rache on 3/31/2018.
 */

public class UploadingImages {
    private Button insert;
    private static Integer number = 0;
    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = (Button) findViewById(R.id.insert);

        insert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_PICK, Uri.parse(
                        "contents://media/internal/images/media"
                ));
                startActivityForResult(intent,PICK_IMAGE);
            }
        } );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            Uri uri = data.getData();
            String x = getPath(uri);
            number = number + 1;
            Integer num = number;

            if(db.insertimage(x,num)){
                Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(getApplicationContext(), " Not Successful", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public String getPath(Uri uri){
        if(uri == null){
            return null;
        }

        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);

        if (cursor != null){
            int column_index = cursor.getColumnIndexorThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        }
        return uri.getPath();
    }
}
