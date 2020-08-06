package com.example.instaclone.Utils;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.MenuItem;

import com.example.instaclone.Home.HomeActivity;
import com.example.instaclone.Notification.NotificationActivity;
import com.example.instaclone.Profile.ProfileActivity;
import com.example.instaclone.R;
import com.example.instaclone.Search.SearchActivity;
import com.example.instaclone.Share.ShareActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import androidx.annotation.NonNull;

public class BottomNavigationViewHelper {

    private static final String TAG = "bottomNavViewHelper";

    public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx){

        Log.d(TAG, "setupBottomNavigationView: settingUpBottomNavigationView");

        bottomNavigationViewEx.enableAnimation(false);
        bottomNavigationViewEx.enableItemShiftingMode(false);
        bottomNavigationViewEx.enableShiftingMode(false);
    }

    public static void enableNavigation(final Context context , BottomNavigationViewEx view){

        view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){

                    case R.id.ic_house:
                        Intent intent1 = new Intent(context, HomeActivity.class); // 0
                        context.startActivity(intent1);
                        break;

                        case R.id.ic_search:
                        Intent intent3 = new Intent(context, SearchActivity.class); // 1
                        context.startActivity(intent3);
                        break;


                    case R.id.ic_circle:
                        Intent intent2 = new Intent(context, ShareActivity.class); // 2
                        context.startActivity(intent2);
                        break;

                    case R.id.ic_alert:
                        Intent intent4 = new Intent(context, NotificationActivity.class); // 3
                        context.startActivity(intent4);
                        break;

                    case R.id.ic_android:
                        Intent intent5 = new Intent(context, ProfileActivity.class); // 4
                        context.startActivity(intent5);
                        break;

                }



                return false;
            }


        });
    }


}
