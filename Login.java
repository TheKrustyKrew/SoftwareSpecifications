package com.example.tsnydej.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by TSNYDEJ on 3/25/2018.
 */

public class Login {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Name = (EditText)findViewById(R.id.Username);
        Password = (EditText)findViewById(R.id.Password);
        Login = (Button)findViewById(R.id.Login);

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String confirmPassword = helper.searchPass(Name);
                if (Password.equals(confirmPassword)){
                    Intent intent = new Intent(this, HomeActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast pass = Toast.makeText(SignUp.this, "Incorrect Password", Toast.LENGTH_SHORT);
                    pass.show();
                }
            }
        });
    }
}
