package com.oysterkode.laundry;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.oysterkode.laundry.databinding.ActivityUser1Binding;

public class User1 extends AppCompatActivity {
    ActivityUser1Binding binding;
    private FirebaseAuth auth;
    private FirebaseAuth sauth;
    Animation topanimation;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUser1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        sauth = FirebaseAuth.getInstance();

        topanimation = AnimationUtils.loadAnimation(this, R.anim.topanimation);
        binding.club.setAnimation(topanimation);
//
//        if(auth.getCurrentUser()!=null){
//            Intent i = new Intent(LogInActivity.this,Admin3.class);
//            startActivity(i);
//            finish();
//        }

        // Creating account Using Email ------>
        binding.CreateAC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.email.getText().toString().isEmpty()) {
                    binding.email.setError("Please enter a Email !!!");
                    return;

                }
                if (binding.password.getText().toString().isEmpty()) {
                    binding.password.setError("Please enter a Password !!!");
                    return;
                }

                auth.createUserWithEmailAndPassword(binding.email.getText().toString()
                        ,binding.password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(User1.this,
                                    "Account Created ",Toast.LENGTH_SHORT).show();
                            binding.password.setText("");
                            binding.email.setText("");
                        }
                    }
                });

            }
        });


        // Sign In using Email ----->
        binding.signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.email.getText().toString().isEmpty()) {
                    binding.email.setError("Please enter a Email !!!");
                    return;

                }
                if (binding.password.getText().toString().isEmpty()) {
                    binding.password.setError("Please enter a Password !!!");
                    return;
                }
                sauth.signInWithEmailAndPassword(binding.email.getText().toString()
                        ,binding.password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful())
                                {
                                    Toast.makeText(User1.this,
                                            "Sign in Successful ",Toast.LENGTH_SHORT).show();

                                    Intent i = new Intent(User1.this, User2.class);
                                    i.putExtra("PRN",binding.password.getText().toString());
                                    startActivity(i);
                                    binding.password.setText("");
                                    binding.email.setText("");
                                }
                                else
                                {
                                    Toast.makeText(User1.this
                                            ,task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                    binding.gifImageView.setVisibility(View.VISIBLE);
                                    binding.gifImageView1.setVisibility(View.INVISIBLE);
                                    binding.logp.setVisibility(View.INVISIBLE);

                                }

                            }
                        });

                       if (binding.email.getText().toString().isEmpty()){
                           binding.logp.setVisibility(View.INVISIBLE);
                         //  binding.gifImageView.setVisibility(View.INVISIBLE);
                         //  binding.gifImageView1.setVisibility(View.VISIBLE);

                       }
                       else if (binding.password.getText().toString().isEmpty()){
                           binding.logp.setVisibility(View.INVISIBLE);
                         //  binding.gifImageView.setVisibility(View.INVISIBLE);
                         //  binding.gifImageView1.setVisibility(View.VISIBLE);

                       } else
                       {
                           binding.logp.setVisibility(View.VISIBLE);
                       }

                binding.gifImageView.setVisibility(View.INVISIBLE);
                binding.gifImageView1.setVisibility(View.VISIBLE);

            }
        });

        String o = getIntent().getStringExtra("xyz");

        if (o == "1"){
            binding.gifImageView.setVisibility(View.VISIBLE);
            binding.gifImageView1.setVisibility(View.INVISIBLE);
            binding.logp.setVisibility(View.INVISIBLE);
        }


    }

    @Override
    public void onBackPressed(){
     finishAffinity();
    }
}