package com.oysterkode.laundry;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity1 extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 2050;
    ImageView logo;
    Animation topanimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);

        topanimation = AnimationUtils.loadAnimation(this, R.anim.topanimation);

        //hooks
        logo = findViewById(R.id.logo);

        logo.setAnimation(topanimation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                Intent intent = new Intent(MainActivity1.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        },SPLASH_TIME_OUT);

    }
}