package com.example.myapplication.entity;


import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAO {
    private DatabaseReference databaseReference;

    public DAO() {
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(khoahoc.class.getSimpleName());
    }
    public Task<Void> add(khoahoc user){
        return  databaseReference.push().setValue(user);
    }
}