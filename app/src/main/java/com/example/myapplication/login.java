package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    private EditText ed_Email, ed_Password;
    private TextView tv_register;
    private Button btnSignIn;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        auth = FirebaseAuth.getInstance();
        ed_Email = findViewById(R.id.ed_Email);
        ed_Password = findViewById(R.id.ed_Password);

        btnSignIn = findViewById(R.id.btn_Signin);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });

        tv_register = findViewById(R.id.tv_register);
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(login.this,register.class);
                startActivity(intent);
            }
        });
    }

    private void userLogin() {
        String email = ed_Email.getText().toString().trim();
        String password = ed_Password.getText().toString().trim();

        if (email.isEmpty()) {
            ed_Email.setError("Email must be filled");
            ed_Email.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ed_Email.setError("Email invalid");
            ed_Email.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            ed_Password.setError("Password must be filled");
            ed_Password.requestFocus();
            return;
        } else if (password.length() < 6) {
            ed_Password.setError("Password must be more than 6 characters");
            ed_Password.requestFocus();
            return;
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    startActivity(new Intent(getBaseContext(), list.class));

                } else {
                    Toast.makeText(login.this, "Login Fail! Check password and email of you", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}