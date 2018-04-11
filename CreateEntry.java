package com.example.android.softwarespecifications;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.net.Uri;
import android.widget.Button;
import android.widget.ImageView;

import static com.example.android.softwarespecifications.MainActivity.helper;

import java.io.BufferedReader;
import java.net.MalformedURLException;

public static String month = "";
public static String day = "";
public static String species = "";
public static String location = "";
String data = "";

public class CreateEntry extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE = 1;
    private static String picturePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entry);

        Button buttonLoadImage = (Button) findViewById(R.id.uploadButton);
        buttonLoadImage.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(i, RESULT_LOAD_IMAGE);

            }
        });
    }

    public void toHomePage(View view){
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.fishImage);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
    }

    public void imageInsert(View v){
        helper.insertImage(picturePath);
    }
    
    
    public void onItemSelected(AdapterView<?> parent, View view, 
    		int pos, long id) {
    	
    	if(parent.getItemAtPosition(pos).equals("snook")) {
    		species = "snook";
    	}
    	else if(parent.getItemAtPosition(pos).equals("tarpon")) {
    		species = "tarpon";
    	}
    	else if(parent.getItemAtPosition(pos).equals("red fish")) {
    		species = "red fish";
    	}
    	
    }
    
    @Override
    protected Void doInBackground(Void...void) {
    	try {
    		URL url = new URL("https://weather.com/weather/tenday/I/Naples"
    				+ "+ FL + USFL0338:1:US");
    		HttpURLConnection httpURLConnection = (HttpURLConnection) 
    				url.openConnection();
    		InputStream inputStream = httpURLConnection.getInputStream();
    		BufferedReader bufferedReader = new BufferedReader
    				(new InputStreamReader(inputStream));
    		
    		String line = "";
    		while(line != null) {
    			line = bufferedReader.readLine();
    			data = data + line;
    		}
    		
    		String date = month + day;
    		JSONArray JA = new JSONArray(data);
    		
    		for(int i = 0; i < JA.length(); i++) {
    			JSONObject JO = (JSONObject) JA.get(i);
    			String currentDate = JO.get("TODAY");
    		}
    		
    		if(date.equals(currentDate)) {
    			date = currentDate;
    		}
    		
    	}
    	catch(MalformedURLException e) {
    		e.printStackTrace();
    		Toast error = Toast.makeText(CreateEntry.this, 
    				"An error has occured", Toast.LENGTH_SHORT);
    	}
    	catch(IOException e) {
    		e.printStackTrace();
    		Toast error = Toast.makeText(CreateEntry.this, 
    				"An error has occured", Toast.LENGTH_SHORT);
    	}
    	catch(JSONException e) {
    		e.printStackTrace();
    		Toast error = Toast.makeText(CreateEntry.this, 
    				"An error has occured", Toast.LENGTH_SHORT);
    	}
    }
}
