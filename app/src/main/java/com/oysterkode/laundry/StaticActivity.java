package com.oysterkode.laundry;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oysterkode.laundry.Models.StudentPRN;
import com.oysterkode.laundry.databinding.ActivityStaticBinding;

public class StaticActivity extends AppCompatActivity {

    ActivityStaticBinding binding;
    FirebaseDatabase database;
    StudentPRN student;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityStaticBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        String StaticStudentPRN = getIntent().getStringExtra("SPrn");
        String StaticDate = getIntent().getStringExtra("Sdate");


        binding.prn2.setText(StaticStudentPRN);
        database = FirebaseDatabase.getInstance();

        database.getReference().child("LaundryInfo").child(StaticStudentPRN).child(StaticDate).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                student = snapshot.getValue(StudentPRN.class);
                binding.shirts1.setText(student.getShirt());
                binding.pants1.setText(student.getPant());
                binding.bedsheet1.setText(student.getBedsheet());
                binding.towel1.setText(student.getTowel());
                binding.blanket1.setText(student.getBlanket());
                binding.total1.setText(student.getTotal());
                binding.pillow1.setText(student.getPillowCover());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });





    }
}