package com.oysterkode.laundry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oysterkode.laundry.databinding.ActivityMainBinding;


import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ProgressDialog progressDialog;
    String a ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading Data");
        progressDialog.setMessage("Please wait ....");
        progressDialog.show();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Students");


        ArrayList <String> Student = new ArrayList<>();
        ArrayAdapter <String> stringArrayAdapter = new ArrayAdapter<>(this, R.layout.main_activity_adapter,Student);

        database.getReference().child("Students").addValueEventListener(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Student.clear();

                for (DataSnapshot snapshot1 : snapshot.getChildren()){

                    String Val = snapshot1.getValue(String.class);
                    Student.add(Val);
//                    Collections.sort(Student);
//
//                    Student.add(stringArrayAdapter);
                }
                stringArrayAdapter.notifyDataSetChanged();
                progressDialog.dismiss();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        binding.myListView.setAdapter(stringArrayAdapter);

        binding.search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                a = binding.inputSearch.getText().toString();
                if(Student.contains(a))
                {
                    binding.inputSearch.setText("");
                    Toast.makeText(MainActivity.this,"User Exits",Toast.LENGTH_SHORT).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            Intent i = new Intent(MainActivity.this,Admin3.class);
                            i.putExtra("PRN",a);
                            startActivity(i);
  //                          finish();
                        }
                    }, 750);

                }
                else
                {
                    Toast.makeText(MainActivity.this,"The User Doest not exits",Toast.LENGTH_SHORT).show();
                    binding.inputSearch.setText("");
                }
            }
        });

       binding.AddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.inputSearch.getText().toString().isEmpty()){
                    binding.inputSearch.setError("please enter a PRN");
                    return;
                }
                String StudentPrn = binding.inputSearch.getText().toString();

                myRef.child(StudentPrn).setValue(StudentPrn);
                binding.inputSearch.setText("");
            }
        });







        binding.myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String a = Student.get(position);
                Intent i = new Intent(MainActivity.this,Admin3.class);
                i.putExtra("PRN",a);
                startActivity(i);
            }
        });




    }

    @Override
    public void onBackPressed(){
        finishAffinity();
    }

}