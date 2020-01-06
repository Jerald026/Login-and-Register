package com.example.halla.loginandregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;
    EditText etEmail, etPass, etCPass;
    Button btnRegister, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPass = (EditText) findViewById(R.id.etPass);
        etCPass = (EditText) findViewById(R.id.etCPass);
        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etEmail.getText().toString().isEmpty() || etPass.getText().toString().isEmpty()
                        || etCPass.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "PLEASE FILL-UP ALL FIELDS!", Toast.LENGTH_SHORT).show();
                } else {
                    String email = etEmail.getText().toString().trim();
                    String pass = etPass.getText().toString().trim();
                    String cPass = etCPass.getText().toString().trim();

                    if (pass.equals(cPass)) {
                        Boolean chkEmail = db.chkEmail(email);
                        if (chkEmail == true) {
                            Boolean insert = db.insert(email, pass);
                            if (insert == true) {
                                etEmail.setText("");
                                etPass.setText("");
                                etCPass.setText("");
                                Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            etPass.setText("");
                            etCPass.setText("");
                            Toast.makeText(getApplicationContext(), "Email already used!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "PASSWORD DID NOT MATCHED!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }
}
