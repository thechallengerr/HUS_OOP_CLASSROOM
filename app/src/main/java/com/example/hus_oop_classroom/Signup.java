package com.example.hus_oop_classroom;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    EditText signupEmail, signupPassword;
    Button Signup;
    String email,password;
    FirebaseAuth firebaseAuth;
    TextView backtoSignin;
    private static final String TAG = "EmailPassword";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signupEmail= findViewById(R.id.signup_email);
        signupPassword= findViewById(R.id.signup_password);
        Signup= findViewById(R.id.bt_signup);
        backtoSignin=findViewById(R.id.backtoSignin);

        firebaseAuth=FirebaseAuth.getInstance();


        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = signupEmail.getText().toString();
                password = signupPassword.getText().toString();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter Email",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Enter Password ",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if(password.length() < 6){
                    Toast.makeText(getApplicationContext(), "Password must be at least 6 characters",
                            Toast.LENGTH_SHORT).show();
                }

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && password.length() > 6){

                    //creating firebase user
                    createUser(email, password);

                }

            }
        });
        backtoSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(Signup.this,LoginPage.class);
                startActivity(i);

            }
        });

    }


    private void createUser(String email, String password) {
        email = signupEmail.getText().toString();
        password = signupPassword.getText().toString();
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            Log.d(TAG, "createUserWithEmail:success");
                            Toast.makeText(getApplicationContext(), "Account created successfully",
                                    Toast.LENGTH_SHORT).show();

                        }
                        else{
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Signup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }
    @Override
    protected void onResume() {
        super.onResume();
    }


}