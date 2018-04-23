package com.example.android.testdatabase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static com.example.android.testdatabase.MainActivity.clubUsers;
import static com.example.android.testdatabase.MainActivity.clubcount;

public class RegisterUser extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
    }

    public Boolean isUnique(){
        Boolean unique = true;
        EditText unEditText = (EditText) findViewById(R.id.Username);
        String userNameValue = unEditText.getText().toString();

        for(int i = 0; i < clubcount; i++){
            if(userNameValue.equals(clubUsers[i].getUsername())){
                Toast.makeText(RegisterUser.this, "Username is taken. Please try new user name.", Toast.LENGTH_SHORT).show();
                unique = false;
                break;
            }
        }

        return unique;
    }

    public void createNewUser(){
        EditText firstName = (EditText) findViewById(R.id.fName);
        String fName = firstName.getText().toString();

        EditText lastName = (EditText) findViewById(R.id.lName);
        String lName = lastName.getText().toString();

        EditText password = (EditText) findViewById(R.id.Password);
        String pass = password.getText().toString();

        EditText username = (EditText) findViewById(R.id.Username);
        String uname = username.getText().toString();

        Users user = new Users(fName, lName, uname, pass, false);
        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        db.insertContact(user);

        clubUsers[clubcount] = user;
        clubcount++;
    }

    public void toLoginPage(View view){
        if(isUnique()) {
            createNewUser();

            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        }
    }

    public void toHomePage(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
