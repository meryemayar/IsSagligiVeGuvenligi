package com.meryemayar54gmail.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    private EditText mName;
    private EditText mEmail;
    private EditText mPassword;

    private FirebaseAuth mAuth;
    private Button mRegisterSbtn;
    private ProgressDialog mProgress;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth= FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Users");

        mProgress= new ProgressDialog(this);
        mName = (EditText) findViewById(R.id.name);
        mEmail=(EditText) findViewById(R.id.r_email);
        mPassword=(EditText)findViewById(R.id.r_password);
        mRegisterSbtn=(Button)findViewById(R.id.RegisterSBtn);


        mRegisterSbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRegister();
            }
        });

    }

    private void startRegister() {
        final String name= mName.getText().toString().trim();
        String email=mEmail.getText().toString().trim();
        String password =mPassword.getText().toString().trim();

        mProgress.setMessage("Signin up...");
        mProgress.show();
        if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
            mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        String user_ıd = mAuth.getCurrentUser().getUid();
                        DatabaseReference current_user_db= mDatabase.child(user_ıd);
                        current_user_db.child("name").setValue(name);

                        mProgress.dismiss();

                        Intent mainIntent= new Intent(RegisterActivity.this, MainActivity.class);
                        mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(mainIntent);

                    }
                }
            });
        }





    }




}
