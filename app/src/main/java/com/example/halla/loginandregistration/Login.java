package com.example.halla.loginandregistration;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText etEmail, etPass;
    Button btnEnter;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        btnEnter = (Button) findViewById(R.id.btnEnter);

        btnEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String pass = etPass.getText().toString().trim();
                Boolean chkEmailPass = db.emailPass(email, pass);

                if (chkEmailPass == true) {
                    Toast.makeText(getApplicationContext(), "Succesfully Login", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "WRONG EMAIL OR PASSWORD!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
