package com.example.ideation.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ideation.Model.User;
import com.example.ideation.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {

    ActivitySignUpBinding binding;
    private FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    String TAG = "jjjjj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =  ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        progressDialog = new ProgressDialog(SignUp.this);
        progressDialog.setTitle("Creating Account");

        binding.signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this, Sign_In.class));
            }
        });

        binding.signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                Log.i(TAG,"id = "+ binding.signUpEmailAddress.getText().toString() +" "+ binding.signUpPassword.getText().toString() );
                auth.createUserWithEmailAndPassword
                        (binding.signUpEmailAddress.getText().toString(),binding.signUpPassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                Log.i(TAG,"task = " + task);
                                if(task.isSuccessful()){

                                    User user = new User(binding.signUpPersonName.getText().toString(),binding.signUpEmailAddress.getText().toString(),
                                            binding.signUpPassword.getText().toString());
                                    Log.i(TAG,user.toString());
                                    String id = task.getResult().getUser().getUid();
                                    Log.i(TAG,"id = "+ id);
                                    database.getReference().child("Users").child(id).setValue(user);
                                    Toast.makeText(SignUp.this,"Sign Up Successfully",Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(SignUp.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                    Log.i(TAG,"task = " + task.getException().getMessage().toString());
                                }
                            }
                        });
            }
        });
    }
}