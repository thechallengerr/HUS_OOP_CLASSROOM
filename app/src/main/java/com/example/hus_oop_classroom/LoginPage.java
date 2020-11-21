package com.example.hus_oop_classroom;

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

public class LoginPage extends AppCompatActivity {
    Button register_button,login_button;
    EditText UserEmail,UserPassword;
    FirebaseAuth firebaseAuth;
    String email,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        login_button= findViewById(R.id.bt_Login);
        register_button= findViewById(R.id.bt_Register);
        UserEmail= findViewById(R.id.ed_username);
        UserPassword= findViewById(R.id.ed_password);
        firebaseAuth = FirebaseAuth.getInstance();

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                email = UserEmail.getText().toString();
                password = UserPassword.getText().toString();


                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter Email",
                            Toast.LENGTH_SHORT).show();
                }

                if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(), "Enter Password ",
                            Toast.LENGTH_SHORT).show();
                }

                if(password.length() < 6){
                    Toast.makeText(getApplicationContext(), "Password must be at least 6 characters",
                            Toast.LENGTH_SHORT).show();
                }

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && password.length() > 6){
                    signUp(email,password);

                }

            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this, Signup.class);
                startActivity(intent);
            }
        });

    }
    private void signUp(String email,String password){
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(LoginPage.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent intent=new Intent(LoginPage.this,HomePage.class);
                            startActivity(intent);

                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Login Failed! Please check your email or password",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

}