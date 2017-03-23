package com.example.rjt.firebasedemo;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myref=database.getReference("message");

        myref.setValue("hello there");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value = dataSnapshot.getValue(String.class);
                Log.w("rjt", "value is " + value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("rjt", "Reading failure", databaseError.toException());
            }
        });

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String Val= dataSnapshot.getValue(String.class);

                Log.w("newval", "Updated value is" +Val);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                Log.w("newval", "Failed to read value", databaseError.toException());
            }
        });

    }
}
