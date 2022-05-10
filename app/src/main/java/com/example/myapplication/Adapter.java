package com.example.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.entity.khoahoc;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

class AdapterLoad extends RecyclerView.Adapter<AdapterLoad.MyViewHolder> {

    private List<khoahoc> mlist;
    private Activity  activity;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

    public AdapterLoad(List<khoahoc> mlist, Activity activity) {
        this.mlist = mlist;
        this.activity = activity;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View viewItem = inflater.inflate(R.layout.layout_item,parent,false);

        return new MyViewHolder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final khoahoc data = mlist.get(position);
        holder.tv_nama.setText("Khóa Học: "+data.getKhoahoc());
        holder.tv_aya.setText("Học Phí: "+data.getHocphi());

        holder.btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        databaseReference.child("khoahoc").child(data.getKey()).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(activity, "Course has been delete successfully", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(activity, "Course has been delete faile", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).setMessage("Delete Course: " + data.getKhoahoc());
                builder.show();
            }
        });
    }
    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tv_nama,tv_aya;
        CardView card_hasil;
        ImageView btn_hapus;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_nama = itemView.findViewById(R.id.tv_khoahoc);
            btn_hapus = itemView.findViewById(R.id.hapus);
            tv_aya = itemView.findViewById(R.id.tv_hocphi);
            card_hasil = itemView.findViewById(R.id.card_hasil);
        }
    }
}
