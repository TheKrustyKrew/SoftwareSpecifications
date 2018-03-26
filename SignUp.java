package com.example.tsnydej.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by TSNYDEJ on 3/25/2018.
 */

public class SignUp {
    DatabaseHelper helper = new DatabaseHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void onSignUpClick(View view){
        if(view.getId() == R.id.signUp){
            EditText name = (EditText)findViewById(R.id.name);
            EditText username = (EditText)findViewById(R.id.username);
            EditText pass1 = (EditText)findViewById(R.id.password);
            EditText pass2 = (EditText)findViewById(R.id.confirmPassword);

            String namestr = name.getText().toString();
            String usernamestr = username.getText().toString();
            String pass1str = pass1.getText().toString();
            String pass2str = pass2.getText().toString();

            if(pass1str.equals(pass2str)){
                Contact c = new Contact();
                c.setName(namestr);
                c.setUsername(usernamestr);
                c.setPassword(pass1str);

                helper.insertContact(c);
            }
            else{
                Toast pass = Toast.makeText(SignUp.this, "Passwords do not match", Toast.LENGTH_SHORT);
                pass.show();
            }

        }
    }

    public void register(View view){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}
