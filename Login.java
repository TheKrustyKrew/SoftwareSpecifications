package com.example.android.testdatabase;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.android.testdatabase.MainActivity.clubAdmins;
import static com.example.android.testdatabase.MainActivity.clubUsers;
import static com.example.android.testdatabase.MainActivity.clubcount;

public class Login extends AppCompatActivity {
    EditText username = (EditText) findViewById(R.id.editText);
    String user = username.getText().toString();

    EditText password = (EditText) findViewById(R.id.editText2);
    String pass = password.getText().toString();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button loginButton = (Button) findViewById(R.id.button);
        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Boolean checkUsername = checkUsername(user, pass);
                if(checkUsername == true){
                    toHome(v);
                }
                else{
                    Toast.makeText(getApplicationContext(),"Incorrect username or password",
                            Toast.LENGTH_SHORT ).show();
                }
            }
        });
    }

    public void toHome(View view){
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }

    public boolean checkUsername(String uName, String pass){

        Cursor cursor = DatabaseHelper.db.rawQuery("Select * from Login Info where Name=? and Password=?",
                new String[]{uName,pass});

        if(cursor.getCount()>0 ){
            cursor.close();
            //DatabaseHelper.db.close();
            return true;
        }
        else{
            cursor.close();
            //DatabaseHelper.db.close();
            return false;
        }

    }
    public void toRegisterPage(View view){
        Intent intent = new Intent(this, RegisterUser.class);
        startActivity(intent);
    }


}
