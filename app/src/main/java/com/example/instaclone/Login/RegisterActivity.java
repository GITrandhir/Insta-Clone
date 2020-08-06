package com.example.instaclone.Login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.instaclone.Models.User;
import com.example.instaclone.Models.UserAccountSettings;
import com.example.instaclone.R;
import com.example.instaclone.Utils.StringManipulation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.instaclone.Utils.StringManipulation.expandUsername;

public class RegisterActivity extends AppCompatActivity {

    private static final String TAG = "RegisterActivity";

    private ProgressBar mProgressBar ;

    private FirebaseAuth mAuth;

    private FirebaseDatabase database ;

    private DatabaseReference databaseReference ;

    private EditText mEmail , mUsername , mPassword ;

    private Button register;

    private String append = "";

    private String userID ;

    String email , username , password ;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_register);

        Log.d(TAG, "onCreate: started.");

        mAuth = FirebaseAuth.getInstance();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();



        mEmail    = findViewById(R.id.input_email);
        mPassword = findViewById(R.id.input_password);
        mUsername = findViewById(R.id.input_username);

        register = (Button) findViewById(R.id.btn_register);

        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mProgressBar.setVisibility(View.GONE);
        // Read from the database


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = mEmail.getText().toString();
                username = mUsername.getText().toString();
                password = mPassword.getText().toString();

                if (email.isEmpty()) {
                    mEmail.setError("Enter a valid Email");
                    mEmail.requestFocus();
                } else if (password.isEmpty()) {
                    mPassword.setError("Enter a valid Password");
                    mPassword.requestFocus();
                }else if(username.isEmpty()){
                    mUsername.setError("Enter a username");
                    mUsername.requestFocus();
                } else if ((email.isEmpty()) && (password.isEmpty()) && (username.isEmpty())) {
                    Toast.makeText(RegisterActivity.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                } else if (!(email.isEmpty() && password.isEmpty() && username.isEmpty())) {

                    signUP();

                } else {
                    Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }










    private void signUP(){

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            //Data();
                            //FirebaseUser user = mAuth.getCurrentUser();

                            addNewUser("","","","");

                            Log.d(TAG, "onComplete: added new user data");
                            finish();
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);

                            startActivity(intent);


                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

    }

    public boolean checkIfUsernameExists(String username , DataSnapshot dataSnapshot){
        Log.d(TAG, "checkIfUsernameExists: checking if"+ username + "already exists.");

        User user = new User();

        /* DataSnapshot loops through database and values created in firebase */

        for(DataSnapshot ds : dataSnapshot.child(userID).getChildren()){
            Log.d(TAG, "checkIfUsernameExists: DataSnapshot" + ds);

            user.setUsername(ds.getValue(User.class).getUsername());

            if(expandUsername(user.getUsername()).equals(username)){
                return true;
            }
        }
            return false;
    }

    public void addNewUser(String email, String username, String description , String profile_photo){

        User user = new User(email , 1 ,userID , StringManipulation.condenseUsername(this.username)); // In sequnce of user class

        databaseReference.child(RegisterActivity.this.getString(R.string.dbname_users))
                         .child(userID)
                         .setValue(user);

        UserAccountSettings settings = new UserAccountSettings(description , username, 0 , 0 , 0 , profile_photo , username);

        databaseReference.child(RegisterActivity.this.getString(R.string.dbname_user_account_setting))
                         .child(userID)
                         .setValue(settings);

    }

  /*  private void createAccount(){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(EmailPasswordActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    } */


}
