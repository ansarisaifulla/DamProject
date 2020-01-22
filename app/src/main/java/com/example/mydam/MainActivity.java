package com.example.mydam;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText txtemail,txtpassword;
    Button btn_login;
    private FirebaseAuth firebaseAuth;
    //WebView w;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Login ");
        txtemail=(EditText)findViewById(R.id.txtemail);
        txtpassword=(EditText)findViewById(R.id.txtpassword);
        btn_login=(Button)findViewById(R.id.btn_login);
        //w=(WebView)findViewById(R.id.web);
        firebaseAuth=FirebaseAuth.getInstance();
        //w.loadUrl("https://www.google.com/");

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email=txtemail.getText().toString().trim();
                String password=txtpassword.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(MainActivity.this,"Please Enter Email",Toast.LENGTH_LONG).show();
                    return;
                }

                if(TextUtils.isEmpty(password))
                {
                    Toast.makeText(MainActivity.this,"Please Enter password",Toast.LENGTH_LONG).show();
                    return;
                }

                if (password.length() < 6) {
                    Toast.makeText(MainActivity.this, "password too short(password length should be greater than 6)", Toast.LENGTH_LONG).show();

                }

                firebaseAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(MainActivity.this,new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Intent i=new Intent(MainActivity.this,MainActivity.class);
                                    startActivity(i);


                                } else {
                                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_LONG).show();

                                }

                                // ...
                            }
                        });

            }
        });

    }


    public void btn_signup(View view) {
        Intent intent=new Intent(this,register.class);
        startActivity(intent);
    }


}

