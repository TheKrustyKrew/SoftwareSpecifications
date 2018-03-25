package com.example.home.softwarespecifications;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    private Button insert;
    EditText number;
    private static final int PICK_IMAGE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        insert = (Button) findViewById(R.id.insert);
        number = (EditText) findViewById(R.id.number);

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
            Integer num = Integer.parseInt(number.getText().toString());
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


