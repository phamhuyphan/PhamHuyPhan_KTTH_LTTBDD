package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.entity.DAO;
import com.example.myapplication.entity.khoahoc;

public class addcourse extends AppCompatActivity {

    private EditText name,sdt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        name = findViewById(R.id.ed_khoahoc);
        sdt = findViewById(R.id.ed_hocphi);

        Button btn_back = findViewById(R.id.eds_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(addcourse.this,list.class);
                startActivity(intent);
            }
        });

        Button btn = findViewById(R.id.ed_save);
        DAO dao = new DAO();
        btn.setOnClickListener( V -> {

            khoahoc khoahoc = new khoahoc(name.getText().toString(),sdt.getText().toString());
            dao.add(khoahoc).addOnSuccessListener(suc ->{
                Toast.makeText(this, "Record is inserted", Toast.LENGTH_SHORT).show();

            }).addOnFailureListener( er ->{
                Toast.makeText(this, ""+er.getMessage(), Toast.LENGTH_SHORT).show();
            });
        });
    }
}