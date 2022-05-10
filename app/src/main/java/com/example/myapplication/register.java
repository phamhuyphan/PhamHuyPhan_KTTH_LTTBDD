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

import com.example.myapplication.entity.khoahoc;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText ed_name, ed_mail,ed_pass;
    private Button btn_register;
    private TextView tv_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        ed_name = findViewById(R.id.ed_khoahoc);
        ed_mail = findViewById(R.id.ed_mail);
        ed_pass = findViewById(R.id.ed_pass);

        btn_register = findViewById(R.id.btn_register);
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                registerUser();

            }
        });

        tv_login = findViewById(R.id.tv_signin);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(register.this,login.class);
                startActivity(intent);
            }
        });
    }

    private void registerUser() {

        String name = ed_name.getText().toString().trim();
        String email = ed_mail.getText().toString().trim();
        String password = ed_pass.getText().toString().trim();

        if (name.isEmpty()) {
            ed_name.setError("Name must be filled");
            ed_name.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            ed_mail.setError("Email must be filled");
            ed_mail.requestFocus();
            return;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            ed_mail.setError("Email invalid");
            ed_mail.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            ed_pass.setError("Password must be filled");
            ed_pass.requestFocus();
            return;
        } else if (password.length() < 6) {
            ed_pass.setError("Password must be more than 6 characters");
            ed_pass.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    khoahoc user = new khoahoc(name,email,password);
                    FirebaseDatabase.getInstance().getReference("Users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(register.this, "User has been register successfully", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(register.this, "Register Fail! Try again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                } else {
                    Toast.makeText(register.this, "Register Fail", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}