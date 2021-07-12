package com.oysterkode.laundry.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oysterkode.laundry.MainActivity;
import com.oysterkode.laundry.databinding.ActivityAddPRNBinding;


public class AddPRNActivity extends AppCompatActivity {


    boolean UserExists = false;
    ActivityAddPRNBinding binding;
//    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddPRNBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        database = FirebaseDatabase.getInstance();

        binding.LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        UserExists = snapshot.child("Students").hasChild(binding.PRN.getText().toString());
                    };
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

                if(UserExists)
                {
                    if(binding.PRN.getText().toString().equals(binding.PassWord.getText().toString()))
                    {
                        Toast.makeText(AddPRNActivity.this,"Login Success",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(AddPRNActivity.this, com.oysterkode.laundry.MainActivity.class);
                        UserExists = true;
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(AddPRNActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                        binding.PRN.setText("");
                        binding.PassWord.setText("");
                    }
                }
                else
                    {
                        Toast.makeText(AddPRNActivity.this,"Try Again",Toast.LENGTH_SHORT).show();
                        binding.PRN.setText("");
                        binding.PassWord.setText("");
                        UserExists = false;
                    }
            }
        });





    }
}