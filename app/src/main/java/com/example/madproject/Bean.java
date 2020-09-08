package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.VideoView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Bean extends AppCompatActivity implements ValueEventListener {
    VideoView videoView;
    LinearLayout info;
    TextView leftTextView,rightTextView, weaponName;
    Intent intent2;
    FrameLayout frame;
    ProgressDialog pd;
    ArrayList<String> left, right;
    String selectedWeapon, selectedCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bean);

        intent2 = getIntent();////////////////////////////////////////////Testing///////////////////////////////////////////////
        selectedWeapon = intent2.getStringExtra("weaponName");
        selectedCategory = intent2.getStringExtra("selectedCategory");

        System.out.println(selectedWeapon+""+selectedCategory+"///////////////////////////////////////////////////////////////");


        videoView = (VideoView) findViewById(R.id.videoView2);
        frame = findViewById(R.id.frame);
        info = findViewById(R.id.Info);
        weaponName = findViewById(R.id.WeaponName);
        weaponName.setText(selectedWeapon);
        System.out.println("Opening "+ weaponName.getText());

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("weapons").child(selectedCategory).child(selectedWeapon);
        //Query q = myRef.limitToFirst(1);
        myRef.addValueEventListener(this);
        // q.addListenerForSingleValueEvent(this);


        pd = new ProgressDialog(Bean.this);
        pd.setMessage("buffering");
        //pd.show();////////////////////////////////////////////Testing///////////////////////////////////////////////

        String s = "https://firebasestorage.googleapis.com/v0/b/fir-fbedb.appspot.com/o/Cash%20prizes%20will%20be%20given%20to%20winners%20(5).mp4?alt=media&token=d13ce2e4-a6a1-4ee1-8536-315aad0903d1";

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(frame);
        Uri uri = Uri.parse(s);
        //videoView.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.samplevideo);

        videoView.setVideoURI(Uri.parse(s));
        //videoView.start();////////////////////////////////////////////Testing///////////////////////////////////////////////
        frame.setVisibility(View.GONE);////////////////////////////////////////////Testing///////////////////////////////////////////////

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                pd.dismiss();

            }
        });


    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {
        right = new ArrayList<>();
        left = new ArrayList<>();
        for (DataSnapshot d : snapshot.getChildren()) {
            left.add(d.getValue().toString());


            View inflator = LayoutInflater.from(Bean.this).inflate(R.layout.row, null);
            info.addView(inflator);
           // viewIds.add(inflator);


            leftTextView = inflator.findViewById(R.id.left);
            leftTextView.setText(d.getKey().toString());


            rightTextView = inflator.findViewById(R.id.right);
            rightTextView.setText(d.getValue().toString());
            // inflator.setOnClickListener(WeaponSelection.this);

        }


    }

    @Override
    public void onCancelled(@NonNull DatabaseError error) {

    }

    //   static void playVideo(VideoView v, String s) {

    // }

}