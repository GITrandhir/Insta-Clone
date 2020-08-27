package com.example.instaclone.Home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.instaclone.Login.LoginActivity;
import com.example.instaclone.R;
import com.example.instaclone.Utils.BottomNavigationViewHelper;
import com.example.instaclone.Utils.SectionsPagerAdapter;
import com.example.instaclone.Utils.UniversalImageLoader;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";
    private static final int ACTIVITY_NUM = 0;

    public FirebaseAuth mAuth; // For checking current user instance

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "onCreate: starting.");

        mAuth = FirebaseAuth.getInstance(); // Initializing firebase auth instance

        initImageLoader();

        setupBottomNavigationView();

        setupViewPager();

        onStart();


    }


    /* If user isn't logged in start login activity */

    public void onStart() {
        super.onStart();

        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }
    }


    private void initImageLoader() {
        UniversalImageLoader universalImageLoader = new UniversalImageLoader(HomeActivity.this);
        ImageLoader.getInstance().init(universalImageLoader.getConfig());
    }


    /* 3 tabs Camera,Icon,Messages */

    private void setupViewPager() {
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new CameraFragment());// 0
        adapter.addFragment(new HomeFragment()); // 1
        adapter.addFragment(new MessagesFragment()); // 2
        ViewPager viewPager = (ViewPager) findViewById(R.id.container);
        viewPager.setAdapter(adapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_messages);

    }

    /* Bottom Navigation View Setup */

    private void setupBottomNavigationView() {
        Log.d(TAG, "setupBottomNavigationView: settingUpBottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomView);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(HomeActivity.this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

}



