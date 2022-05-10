package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.entity.khoahoc;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class list extends AppCompatActivity {

    AdapterLoad  adapterLoad;
    DatabaseReference data = FirebaseDatabase.getInstance().getReference();
    ArrayList<khoahoc> arrayList;
    RecyclerView tv_tampill;
    FloatingActionButton tambal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        tambal = findViewById(R.id.btn_tambal);
        tambal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(list.this, addcourse.class);
                startActivity(intent);
            }
        });

        tv_tampill = findViewById(R.id.tv_tampill);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        tv_tampill.setLayoutManager(manager);
        tv_tampill.setItemAnimator(new DefaultItemAnimator());
        tempiData();

    }

    private void tempiData() {
        data.child("khoahoc").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                arrayList = new ArrayList<>();
                for(DataSnapshot item: snapshot.getChildren()){
                    khoahoc kh = item.getValue(khoahoc.class);
                    kh.setKey(item.getKey());
                    arrayList.add(kh);
                }
                adapterLoad = new AdapterLoad(arrayList,list.this);
                tv_tampill.setAdapter(adapterLoad);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}