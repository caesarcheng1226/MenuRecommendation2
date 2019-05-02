package com.example.menurecommendation;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements RecipeFragment.DataPassListener {

    public final int REQUEST_CODE = 1;
    private String welcomeReply;
    MapFragment mapFragment;
    RecipeFragment recipeFragment;
    ProfileFragment profileFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(MenuItem item) {
            Fragment selectedFragment = null;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    break;
                case R.id.navigation_map:
                    if (mapFragment == null){
                        mapFragment = new MapFragment();
                    }
                    selectedFragment = mapFragment;
                    break;
                case R.id.navigation_recipe:
                    if (recipeFragment == null)
                        recipeFragment = new RecipeFragment();
                    selectedFragment = recipeFragment;
                    break;
                case R.id.navigation_profile:
                    if (profileFragment == null)
                        profileFragment = new ProfileFragment();
                    selectedFragment = profileFragment;
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Boolean isFirstRun = getSharedPreferences("PREFERENCE", MODE_PRIVATE)
                .getBoolean("isFirstRun", true);
        if (!isFirstRun) {
            //show start activity
            getSharedPreferences("PREFERENCE", MODE_PRIVATE).edit()
                    .putBoolean("isFirstRun", false).apply();
            Log.d("New user", "new User");
            startActivityForResult(new Intent(MainActivity.this, FirstLaunchActivity.class), REQUEST_CODE);
        } else {
            setContentView(R.layout.activity_main_page);
            BottomNavigationView navView = findViewById(R.id.nav_view);
            navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                welcomeReply = data.getStringExtra(FirstLaunchActivity.WELCOME_Reply);
                setContentView(R.layout.welcome_screen);
                TextView welcomeText = findViewById(R.id.welcome);
                welcomeText.setText(welcomeReply);
                fadingEffect(welcomeText);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        setContentView(R.layout.activity_main_page);
                        BottomNavigationView navView = findViewById(R.id.nav_view);
                        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
                    }
                }, 4000);
            }
        }
    }

    private void fadingEffect(TextView txtView){
        AlphaAnimation fadeIn = new AlphaAnimation(0.0f , 1.0f ) ;
        fadeIn.setDuration(1500);
        txtView.startAnimation(fadeIn);
    }

    @Override
    public void passData(String data) {
        RecipeSearchFragment recipeSearchFragment = new RecipeSearchFragment();
        Bundle args = new Bundle();
        args.putString(RecipeSearchFragment.DATA_RECEIVE, data);
        recipeSearchFragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, recipeSearchFragment)
                .addToBackStack(null)
                .commit();
    }
}
