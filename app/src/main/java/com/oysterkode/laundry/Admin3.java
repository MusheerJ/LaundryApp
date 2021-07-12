package com.oysterkode.laundry;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.oysterkode.laundry.AdaptersStudent.StudentPRNAdapter;
import com.oysterkode.laundry.Models.StudentPRN;
import com.oysterkode.laundry.databinding.ActivityAdmin3Binding;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class Admin3 extends AppCompatActivity {

    ArrayList <StudentPRN> users;
    StudentPRNAdapter userAdapter;
    ActivityAdmin3Binding binding;
    FirebaseDatabase database;
    DatabaseReference myRef, sref;
    ProgressDialog progressDialog;
    static int TotalValue ;
    String t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdmin3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        progressDialog = new ProgressDialog(com.oysterkode.laundry.Admin3.this);

        progressDialog.show();
//        String s = "0000000";

//        String searchIntent = getIntent().getStringExtra("search");

        database = FirebaseDatabase.getInstance();
 //       database.setPersistenceEnabled(true);
//        if (searchIntent.equals(""))
//        {
//            s = getIntent().getStringExtra("PRN");
//        }\

        String s = getIntent().getStringExtra("PRN");
        binding.prnshow.setText(s);
        users = new ArrayList<>();
        userAdapter = new StudentPRNAdapter(this,users);
        binding.recyclerView.setAdapter(userAdapter);


        database.getReference("LaundryInfo").child(s).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int sumOf = 0;
                String date1 = "";
                users.clear();
                for(DataSnapshot snapshot1 : snapshot.getChildren() )
                {
                   StudentPRN user =  snapshot1.getValue(StudentPRN.class);
                   sumOf = sumOf + Integer.parseInt(user.getTotal());
                   users.add(user);
                   date1 = user.getDate();
                }

                if (date1.equals(new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                        .format(new Date())))
                {
                    binding.addclothing.setVisibility(View.INVISIBLE);
                }
                userAdapter.notifyDataSetChanged();
                TotalValue = 24 - sumOf;


                binding.remainshow.setText("Remaining : " + String.valueOf(TotalValue));
                progressDialog.dismiss();



            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        int SumOf = 0;
//        for (StudentPRN us1 : users)
//        {
//            SumOf = SumOf +  Integer.parseInt(us1.getTotal());
//        }


        /*
        myRef.addValueEventListener(new ValueEventListener() {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        // This method is called once with the initial value and again
        // whenever data at this location is updated.
        String value = dataSnapshot.getValue(String.class);
        Log.d(TAG, "Value is: " + value);
    }
         */

        sref = database.getReference().child("Studentinfo").child(s).child("h");
      //  sref.keepSynced(true);
        sref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.Hostel.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



       myRef = database.getReference().child("Studentinfo").child(s).child("n");
     //  myRef.keepSynced(true);
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                binding.Name.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
//        binding.Name.setText(database.getReference("Profile").child(s).getKey());

        binding.addclothing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(com.oysterkode.laundry.Admin3.this, com.oysterkode.laundry.Admin2.class);
                i.putExtra("PRNN", s);
                startActivity(i);
               finish();
            }
        });

        //ADDPRN



        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("LaundryInfo");

     //   String d = "";
     //   d.equals(new SimpleDateFormat("MMMM",Locale.getDefault())
     //           .format(new Date()));

     //   binding.month.setText(d);



//        StudentsPRN Prn = new StudentsPRN(binding.PRN.getText().toString());
//        PRN.setStudentPRN(binding.PRN.getText().toString());

     //   binding.Add.setOnClickListener(new View.OnClickListener() {
   //         @Override
      //      public void onClick(View v) {
//                String PRN = binding.PRN.getText().toString();
//                database.getReference().child("Students").setValue(Prn);
     //           myRef.push().setValue(binding.prnshow.getText().toString());
    //            binding.prnshow.setText("");

//                Toast toast = new Toast("ADDd")
    //        }
   //     });

//        Intent b = getIntent();

    }
}