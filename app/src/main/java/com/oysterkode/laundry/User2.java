package com.oysterkode.laundry;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;

import pl.droidsonroids.gif.GifImageView;


public class User2 extends AppCompatActivity {
    StorageReference storageReference;
    DatabaseReference databaseReference;

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference();

    String NN ;
    String u;
    private StorageReference mStorage;
    Button b1,b2;
    ImageView i1;
    ImageView gif1;
    TextView t1,N1;
    ProgressBar p1;
    Button b3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user2);

        p1 = findViewById(R.id.pro);
        b2 = findViewById(R.id.about);
        t1 = findViewById(R.id.link);
        b1 = findViewById(R.id.go);
        i1 = findViewById(R.id.fireimage);
        gif1 = findViewById(R.id.gif);

        String s = getIntent().getStringExtra("PRN");

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(User2.this, Aboutus.class);
                startActivity(i);
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(User2.this, Admin3.class);
                i.putExtra("PRN", s);
                startActivity(i);
            }
        });
        mStorage = FirebaseStorage.getInstance().getReference().child("Show/add.jpg");
      //  mStorage.keepSynced(true);
        try {
            final File localFile = File.createTempFile("add","jpg");
            mStorage.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            gif1.setImageBitmap(bitmap);
                        }
                    });


        } catch (IOException e) {
            e.printStackTrace();
        }



        myRef = FirebaseDatabase.getInstance().getReference().child("notice").child("noti");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                N1.setText(snapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




     /*   myRef = FirebaseDatabase.getInstance().getReference().child("flyer");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String u = dataSnapshot.child("url").getValue().toString();
            }

            @Override
            public void onCancelled (@NonNull DatabaseError error){

            }
        });


      */
     //   gif1.setOnClickListener(new View.OnClickListener() {
      //      @Override
      //      public void onClick(View v) {
     //           Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(u));
     //           startActivity(i);
     //       }
     //   });

        N1 = findViewById(R.id.noti1);
        N1.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        N1.setSelected(true);




    }

    @Override
    public void onBackPressed(){
        Intent i = new Intent(User2.this, User1.class );
        startActivity(i);
        i.putExtra("xyz", 1);
        finish();
    }
}