package com.example.madproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout rifles, shotguns, pistols, assaultRifles,
            machineGuns, submachineGuns, revolvers, snipers;
    Intent intent;
    String category;
    String[] s;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("started main");


        rifles = (LinearLayout) findViewById(R.id.Rifles);
        shotguns = (LinearLayout) findViewById(R.id.Shotguns);
        pistols = (LinearLayout) findViewById(R.id.Pistols);
        assaultRifles = (LinearLayout) findViewById(R.id.AssaultRifles);
        machineGuns = (LinearLayout) findViewById(R.id.MachineGuns);
        submachineGuns = (LinearLayout) findViewById(R.id.SubmachineGuns);
        revolvers = (LinearLayout) findViewById(R.id.Revolvers);
        snipers = (LinearLayout) findViewById(R.id.Snipers);


        rifles.setOnClickListener(MainActivity.this);
        shotguns.setOnClickListener(MainActivity.this);
        pistols.setOnClickListener(MainActivity.this);
        assaultRifles.setOnClickListener(MainActivity.this);
        machineGuns.setOnClickListener(MainActivity.this);
        submachineGuns.setOnClickListener(MainActivity.this);
        revolvers.setOnClickListener(MainActivity.this);
        snipers.setOnClickListener(MainActivity.this);



    }

    @Override
    public void onClick(View v) {
        category = getResources().getResourceName(v.getId());
        category = splitCamelCase(category.substring(category.lastIndexOf('/') + 1));
        intent = new Intent(MainActivity.this, WeaponSelection.class);
        //System.out.println("********** Entering "+category+" **********");

        switch (v.getId()) {
            case R.id.Rifles:
                break;

            case R.id.Shotguns:
                break;

            case R.id.Pistols:
                break;

            case R.id.AssaultRifles:
                break;

            case R.id.MachineGuns:
                break;

            case R.id.SubmachineGuns:
                break;

            case R.id.Revolvers:
                break;

            case R.id.Snipers:
                break;

        }

        intent.putExtra("category", category);
        System.out.println("ended main");
        startActivity(intent);

    }

    static String splitCamelCase(String s) {
        return s.replaceAll(
                String.format("%s|%s|%s",
                        "(?<=[A-Z])(?=[A-Z][a-z])",
                        "(?<=[^A-Z])(?=[A-Z])",
                        "(?<=[A-Za-z])(?=[^A-Za-z])"
                ),
                " "
        );
    }

}