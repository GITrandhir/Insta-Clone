package com.example.instaclone.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instaclone.Home.HomeActivity;
import com.example.instaclone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    TextView mSignUpActivity;

    private FirebaseAuth mAuth ;

    private Button bLogin ;

    private EditText mEmail , mPassword ;

    private String sEmail , sPassword ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        mSignUpActivity = findViewById(R.id.link_signup);

        bLogin = findViewById(R.id.btn_login);

        mEmail    = findViewById(R.id.input_email);
        mPassword = findViewById(R.id.input_password);


        mSignUpActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this , RegisterActivity.class));
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sEmail = mEmail.getText().toString();
                sPassword = mPassword.getText().toString();

                if(sEmail.isEmpty()){
                    mEmail.setError("Enter a valid Email");
                    mEmail.requestFocus();
                }else if(sPassword.isEmpty()){
                    mPassword.setError("Enter a valid Password");
                    mPassword.requestFocus();
                }else if((sEmail.isEmpty()) && (sPassword.isEmpty())){
                    Toast.makeText(LoginActivity.this,"Fields are empty",Toast.LENGTH_SHORT).show();
                }else if(!(sEmail.isEmpty() && sPassword.isEmpty())){

                    login();

                }else {
                    Toast.makeText(LoginActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void login(){

        mAuth.signInWithEmailAndPassword(sEmail, sPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success,

                            FirebaseUser user = mAuth.getCurrentUser();

                            updateUI(user);

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }

    private void updateUI(FirebaseUser user){
        if(user != null){
            startActivity(new Intent(LoginActivity.this, HomeActivity.class));
        }
    }


}
