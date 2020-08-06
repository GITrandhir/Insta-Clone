package com.example.instaclone.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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


    private static final String TAG = "LoginActivity";

    private FirebaseAuth mAuth;

    private FirebaseUser user;

    private ProgressBar mProgressBar ;

    private EditText mEmail , mPassword ;

    private TextView register ;

    TextView linkSignUp ;

    private Button logIN ;

    String email , password ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        Log.d(TAG, "onCreate: started.");

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        register = (TextView) findViewById(R.id.link_signup);

        mEmail    = findViewById(R.id.input_email);
        mPassword = findViewById(R.id.input_password);

        linkSignUp = (TextView) findViewById(R.id.link_signup);

        logIN = (Button) findViewById(R.id.btn_login);

        mProgressBar.setVisibility(View.GONE);

        mAuth = FirebaseAuth.getInstance();

        // Retrieves user inputs

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });


        logIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = mEmail.getText().toString();
                password = mPassword.getText().toString();

                if(email.isEmpty()){
                    mEmail.setError("Enter a valid Email");
                    mEmail.requestFocus();
                }else if(password.isEmpty()){
                    mPassword.setError("Enter a valid Password");
                    mPassword.requestFocus();
                }else if((email.isEmpty()) && (password.isEmpty())){
                    Toast.makeText(LoginActivity.this,"Fields are empty",Toast.LENGTH_SHORT).show();
                }else if(!(email.isEmpty() && password.isEmpty())){
                    signIN();

                }else {
                    Toast.makeText(LoginActivity.this,"Error",Toast.LENGTH_SHORT).show();
                }

            }
        });

        linkSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });


    }






   private void signIN() {
        mAuth.signInWithEmailAndPassword(email , password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            finish();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);

                            startActivity(intent);
                            // If user will press back he/she won't be able to comeback to login activity
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });
    }



}
