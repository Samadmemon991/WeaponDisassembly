package com.example.madproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WeaponSelection extends AppCompatActivity implements View.OnClickListener, ValueEventListener {
    TextView categoryName, weaponName;
    LinearLayout weaponList;
    ArrayList viewIds;
    Intent intent;
    View inflator;
    ArrayList<String> dataWeaponlist;
    String[] arr;
    String selectedCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapon_selection);
        System.out.println("started weapon selection");

        intent = getIntent();
        selectedCategory = intent.getStringExtra("category");

        categoryName = findViewById(R.id.CategoryName);
        categoryName.setText(selectedCategory);



        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("weapons").child(selectedCategory);
        myRef.addValueEventListener(this);



        viewIds = new ArrayList<View>();
        weaponList = findViewById(R.id.WeaponList);



       /* for (int i = 0; i < 5; i++) {
            inflator = LayoutInflater.from(WeaponSelection.this).inflate(R.layout.card, null);
            weaponList.addView(inflator);
            viewIds.add(inflator);
            weaponName = inflator.findViewById(R.id.text);
            weaponName.setText(intent.getStringExtra("category") + "k" + i);
            inflator.setOnClickListener(WeaponSelection.this);
             }*/


    }

    @Override
    public void onClick(View v) {
        int i = 0;
        Intent intenti;
        String weaponName = "";

        for (Object o : viewIds) {
            if (v.equals(viewIds.get(i))) {
                try {
                    View dumyView = (View) viewIds.get(i);
                    TextView dumyText = (TextView) dumyView.findViewById(R.id.text);
                    weaponName = (String) dumyText.getText();
                    System.out.println("clicked "+weaponName);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            i++;
        }

        intenti = new Intent(WeaponSelection.this, Bean.class);
        intenti.putExtra("weaponName", weaponName);
        intenti.putExtra("selectedCategory", selectedCategory);
        startActivity(intenti);
        System.out.println("ended weapon selection");


     /*   if (v.equals(viewIds.get(0))) {
            System.out.println("one");
        } else if (v.equals(viewIds.get(1))) {
            System.out.println("two");
        } else if (v.equals(viewIds.get(2))) {
            System.out.println("three");
        } else if (v.equals(viewIds.get(3))) {
            System.out.println("chala");
        }*/


    }

    @Override
    public void onDataChange(@NonNull DataSnapshot snapshot) {


        dataWeaponlist = new ArrayList<>();
        for (DataSnapshot d : snapshot.getChildren()) {
            dataWeaponlist.add(d.getValue().toString());


            inflator = LayoutInflater.from(WeaponSelection.this).inflate(R.layout.card, null);
            weaponList.addView(inflator);
            viewIds.add(inflator);
            weaponName = inflator.findViewById(R.id.text);
            weaponName.setText(d.getValue().toString());
            inflator.setOnClickListener(WeaponSelection.this);

        }

        //System.out.println(snapshot.getChildrenCount() + "//////////Database Testing///");

    }


    @Override
    public void onCancelled(@NonNull DatabaseError error) {
        System.out.println("The read failed: " + error.getCode());

    }
}