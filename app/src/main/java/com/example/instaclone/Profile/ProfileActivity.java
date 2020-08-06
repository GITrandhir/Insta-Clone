package com.example.instaclone.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.instaclone.Login.LoginActivity;
import com.example.instaclone.R;
import com.example.instaclone.Utils.BottomNavigationViewHelper;
import com.example.instaclone.Utils.GridImageAdapter;
import com.example.instaclone.Utils.UniversalImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    private static final String TAG = "SearchActivity";
    private static final int ACTIVITY_NUM = 4;
    private static final int NUM_GRID_COLOUMNS = 3 ;

    public FirebaseAuth mAuth;

    private ImageView signOut ;

    private ImageView profilePhoto;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);

        Button editProfile = (Button) findViewById(R.id.editProfileButton);

        signOut = (ImageView) findViewById(R.id.signOut);

        mAuth = FirebaseAuth.getInstance();

        Log.d(TAG, "onCreate: started.");

        setupBottomNavigationView();

        //setupToolbar();

        setupActivityWidgets();

        setProfileImage();

        tempGridSetup();

        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProfileActivity();
            }
        });

        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                startActivity(new Intent(ProfileActivity.this , LoginActivity.class));
            }
        });
    }



    private void tempGridSetup(){
        ArrayList<String> imgURLs = new ArrayList<>();
        imgURLs.add("https://i.pinimg.com/originals/66/cf/ce/66cfce84108f3b3f2357473047946b1f.jpg");
        imgURLs.add("");
        imgURLs.add("https://c1.staticflickr.com/5/4276/34102458063_7be616b993_o.jpg");
        imgURLs.add("http://i.imgur.com/EwZRpvQ.jpg");
        imgURLs.add("http://i.imgur.com/JTb2pXP.jpg");
        imgURLs.add("https://i.redd.it/59kjlxxf720z.jpg");
        imgURLs.add("https://i.redd.it/pwduhknig00z.jpg");
        imgURLs.add("https://i.redd.it/clusqsm4oxzy.jpg");
        imgURLs.add("https://i.redd.it/svqvn7xs420z.jpg");
        imgURLs.add("http://i.imgur.com/j4AfH6P.jpg");
        imgURLs.add("https://i.redd.it/89cjkojkl10z.jpg");
        imgURLs.add("https://i.redd.it/aw7pv8jq4zzy.jpg");

        setupImageGrid(imgURLs);
    }


    private void setupImageGrid(ArrayList<String> imgURLs){
        GridView gridView = (GridView) findViewById(R.id.gridView);

        int gridWidth = getResources().getDisplayMetrics().widthPixels;

        int imageWidth = gridWidth/NUM_GRID_COLOUMNS ;

        gridView.setColumnWidth(imageWidth);

        GridImageAdapter adapter = new GridImageAdapter(ProfileActivity.this,R.layout.layout_grid_imageview,"",imgURLs);

        gridView.setAdapter(adapter);
    }


    private void setProfileImage(){
        Log.d(TAG, "setProfileImage: setting profile photo");
        String imgURL = "https://pluralsight2.imgix.net/paths/images/android-53f8da146d.png";
        UniversalImageLoader.setImage(imgURL,profilePhoto,null,"");
    }

    private void setupActivityWidgets(){

        profilePhoto = (ImageView) findViewById(R.id.profilephoto);
    }

   /* private void setupToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolBar);

        setSupportActionBar(toolbar);

        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Log.d(TAG, "onMenuItemClick: menu item clicked" + item);

                switch (item.getItemId()) {

                    case R.id.signOut:
                        Log.d(TAG, "onMenuItemClick: navigating to profile preferences");
                        Toast.makeText(ProfileActivity.this,"Clicked",Toast.LENGTH_SHORT).show();
                        mAuth.signOut();
                }
                return false;
            }
        });
    } */




    /* Bottom Navigation View Setup */

    private void setupBottomNavigationView(){
        Log.d(TAG, "setupBottomNavigationView: settingUpBottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomView);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(ProfileActivity.this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.profile_menu,menu);


        return true ;
    } */

  //Starts Edit Profile
  private void EditProfileActivity(){

      Intent intent;
      intent = new Intent(ProfileActivity.this, EditProfile.class);

      startActivity(intent);


  }

}
