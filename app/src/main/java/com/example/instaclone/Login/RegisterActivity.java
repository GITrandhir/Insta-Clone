package com.example.instaclone.Login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.instaclone.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth ;

    private EditText mEmail , mUsername , mPassword ;

    private Button register;

    private  String sEmail , sUsername , sPassword ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        register = (Button) findViewById(R.id.btn_register);

        mEmail    = findViewById(R.id.input_email);
        mPassword = findViewById(R.id.input_password);
        mUsername = findViewById(R.id.input_username);



        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sEmail = mEmail.getText().toString();
                sUsername = mUsername.getText().toString();
                sPassword = mPassword.getText().toString();

                if (sEmail.isEmpty()) {
                    mEmail.setError("Enter a valid Email");
                    mEmail.requestFocus();
                } else if (sPassword.isEmpty()) {
                    mPassword.setError("Enter a valid Password");
                    mPassword.requestFocus();
                }else if(sUsername.isEmpty()){
                    mUsername.setError("Enter a username");
                    mUsername.requestFocus();
                } else if ((sEmail.isEmpty()) && (sPassword.isEmpty()) && (sUsername.isEmpty())) {
                    Toast.makeText(RegisterActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                } else if (!(sEmail.isEmpty() && sPassword.isEmpty() && sUsername.isEmpty())) {

                    register();

                } else {
                    Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void register(){

        mAuth.createUserWithEmailAndPassword(sEmail, sPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success

                            // Starting Login Activity
                            finish();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }

}
