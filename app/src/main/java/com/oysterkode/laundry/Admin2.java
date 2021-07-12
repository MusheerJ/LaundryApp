package com.oysterkode.laundry;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.oysterkode.laundry.Models.StudentPRN;
import com.oysterkode.laundry.databinding.ActivityAdmin2Binding;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Admin2 extends AppCompatActivity {

    FirebaseDatabase database, db;
    DatabaseReference myRef;
    ActivityAdmin2Binding binding;
    String shirt, pant, towel , blanket, bedSheet, pillowcover,total;
    int shi = 0, pi = 0, ti = 0, bi = 0, bei = 0, x = 0, toti, tat;
    //    Button Add = (Button) findViewById(R.id.addcloth);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        database = FirebaseDatabase.getInstance();
        db = FirebaseDatabase.getInstance();
       // database.setPersistenceEnabled(true);
        binding = ActivityAdmin2Binding.inflate(getLayoutInflater());

        binding.addcloth.setVisibility(View.INVISIBLE);

        setContentView(binding.getRoot());
        String s = getIntent().getStringExtra("PRNN");

//        t1 = findViewById(R.id.prn1);
        binding.prn1.setText(s);


//        td = findViewById(R.id.date1);
        String date = new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                .format(new Date());
        binding.date1.setText(new SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
                .format(new Date())

        );

//        tsh = findViewById(R.id.shirts1);
        binding.shirts1.setText(String.valueOf(x));
//        tp = findViewById(R.id.pants1);
        binding.pants1.setText(String.valueOf(x));
//        tt = findViewById(R.id.towel1);
        binding.towel1.setText(String.valueOf(x));
//        tb = findViewById(R.id.blanket1);
        binding.blanket1.setText(String.valueOf(x));
//        tbe = findViewById(R.id.bedsheet1);
        binding.bedsheet1.setText(String.valueOf(x));
        binding.pillow1.setText(String.valueOf(x));

//        ct = findViewById(R.id.ctotal);
        binding.ctotal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                shirt = binding.shirts1.getText().toString();
                shi = Integer.parseInt(shirt);

                pant = binding.pants1.getText().toString();
                pi = Integer.parseInt(pant);

                towel = binding.towel1.getText().toString();
                ti = Integer.parseInt(towel);

                blanket = binding.blanket1.getText().toString();
                bi = Integer.parseInt(blanket);

                bedSheet = binding.bedsheet1.getText().toString();
                bei = Integer.parseInt(bedSheet);

                pillowcover = binding.pillow1.getText().toString();

                int toi = shi + pi + ti + bi + bei + Integer.parseInt(pillowcover);

                String total = String.valueOf(toi);
                binding.total1.setText(total);
             //   binding.addcloth.setVisibility(View.VISIBLE);

                if (toi < 24){
                    binding.addcloth.setVisibility(View.VISIBLE);
                }
                /*
         this.Date = date;
        this.Total = total;
        this.StudentPRN = studentPRN;
        this.pant = pant;
        this.shirt = shirt;
        this.bedsheet = bedsheet;
        this.towel = towel;
        this.blanket = blanket;
                */

                StudentPRN SomeStudent = new StudentPRN(date,total,s,pant,shirt,bedSheet,towel,blanket,pillowcover);

            }


        });


        binding.addcloth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shirt = binding.shirts1.getText().toString();
                shi = Integer.parseInt(shirt);

                pant = binding.pants1.getText().toString();
                pi = Integer.parseInt(pant);

                towel = binding.towel1.getText().toString();
                ti = Integer.parseInt(towel);

                blanket = binding.blanket1.getText().toString();
                bi = Integer.parseInt(blanket);

                bedSheet = binding.bedsheet1.getText().toString();
                bei = Integer.parseInt(bedSheet);


                pillowcover = binding.pillow1.getText().toString();

                int toi = shi + pi + ti + bi + bei + Integer.parseInt(pillowcover);



                String total = String.valueOf(toi);
                binding.total1.setText(total);

                if (toi > 24){
                    binding.addcloth.setVisibility(View.INVISIBLE);
                }



                StudentPRN SomeStudent = new StudentPRN(date,total,s,pant,shirt,bedSheet,towel,blanket,pillowcover);
                database.getReference().child("LaundryInfo").child(SomeStudent.getStudentPRN()).child(SomeStudent.getDate()).setValue(SomeStudent);
                db.getReference().child("day").child(SomeStudent.getDate()).child(SomeStudent.getStudentPRN()).setValue(SomeStudent);


                Intent i = new Intent(com.oysterkode.laundry.Admin2.this,MainActivity.class);
                startActivity(i);
                finish();




            }
        });


    }
    @Override
    public void onBackPressed(){
        finish();
    }
}