package com.example.mydam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {

    EditText txt_fullname,txt_username,txt_email,txt_password;
    Button btn_register;
    EditText txt_confirmpassword;
    RadioButton radiogendermale,radiogenderfemale;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    String gender="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().setTitle("Sign Up");
        txt_fullname=(EditText)findViewById(R.id.txt_fullname);
        txt_username=(EditText)findViewById(R.id.txt_username);
        txt_email=(EditText)findViewById(R.id.txt_email);
        txt_password=(EditText)findViewById(R.id.txt_password);
        txt_confirmpassword=(EditText)findViewById(R.id.txt_confirmpassword);
        btn_register=(Button)findViewById(R.id.btn_register);
        radiogendermale=(RadioButton)findViewById(R.id.radio_gendermale);
        radiogenderfemale=(RadioButton)findViewById(R.id.radio_genderfemale);

        databaseReference=FirebaseDatabase.getInstance().getReference("user");
        firebaseAuth=FirebaseAuth.getInstance();


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullname = txt_fullname.getText().toString().trim();
                final String username = txt_username.getText().toString().trim();
                final String email = txt_email.getText().toString().trim();
                final String password = txt_password.getText().toString().trim();
                final String confirmpassword = txt_confirmpassword.getText().toString().trim();
                if (radiogendermale.isChecked()) {
                    gender = "Male";
                }
                if (radiogenderfemale.isChecked()) {
                    gender = "Female";
                }

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(register.this, "Please Enter Email", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(register.this, "Please Enter password", Toast.LENGTH_LONG).show();
                    return;
                }

                if (TextUtils.isEmpty(confirmpassword)) {
                    Toast.makeText(register.this, "Please Enter Confirm password", Toast.LENGTH_LONG).show();
                    return;
                }

               /* if(TextUtils.isEmpty(username))
                {
                    Toast.makeText(signup.this,"Please Enter username",Toast.LENGTH_LONG).show();
                return;
                }  */

                if (password.length() < 6) {
                    Toast.makeText(register.this, "password too short(password length should be greater than 6)", Toast.LENGTH_LONG).show();

                }

                if (password.equals(confirmpassword))
                {

                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {

                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                        user information = new user(
                                                fullname,
                                                username,
                                                email,
                                                gender

                                        );

                                        FirebaseDatabase.getInstance().getReference("user")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(information).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                                Toast.makeText(register.this, "Registeration Successfull", Toast.LENGTH_LONG).show();
                                                Intent i=new Intent(register.this,MainActivity.class);
                                                startActivity(i);

                                            }
                                        });

                                    } else {

                                        Toast.makeText(register.this,"Authentication Failed",Toast.LENGTH_LONG).show();
                                    }

                                    // ...
                                }
                            });

                }
            }



        });


    }


}

