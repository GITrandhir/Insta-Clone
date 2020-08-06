package com.example.instaclone.Profile;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.instaclone.R;
import com.example.instaclone.Utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.instaclone.R.layout.activity_editprofile;

public class EditProfile extends AppCompatActivity{

    private ImageView profilePhoto ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(activity_editprofile);

        profilePhoto = (ImageView) findViewById(R.id.profile_photo);

        // bcak arrow for navigating back to Profile Activity

        ImageView backArrow = (ImageView) findViewById(R.id.backArrow) ;

        backArrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        setProfileImage();

    }



    private void setProfileImage(){

        String imgURL = "https://pluralsight2.imgix.net/paths/images/android-53f8da146d.png";

        UniversalImageLoader.setImage(imgURL,profilePhoto,null,"");

    }



}
