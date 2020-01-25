package com.example.mydam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginSuccess extends AppCompatActivity {
    TextView textViewUser,textViewLivedata;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success);
        final Intent intent = getIntent();
        final String msg = intent.getStringExtra("EXTRA_MESSAGE");
        textViewUser = findViewById(R.id.tvuser);
        textViewUser.setText("Welcome, "+msg);
        textViewLivedata = findViewById(R.id.live_data_tv);
        mDatabase = FirebaseDatabase.getInstance().getReference("Current Value");
    }

    //Getting live data from firebase
    @Override
    protected void onStart() {
        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //Retrieving live data
                for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
                    String liveData = dataSnapshot1.getValue().toString();
                    textViewLivedata.setText(liveData);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        super.onStart();
    }
}
