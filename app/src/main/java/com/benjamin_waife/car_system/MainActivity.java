package com.benjamin_waife.car_system;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 2500;

    //Variables
    Animation topAnim, bottomAnim;
    TextView name, slogan;

    SharedPreferences boardingScreen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //logo and slogan
        name = findViewById(R.id.name);
        slogan = findViewById(R.id.slogan);

        name.setAnimation(topAnim);
        slogan.setAnimation(bottomAnim);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                boardingScreen = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);

                boolean isFirstTime = boardingScreen.getBoolean("firstTime", true);
                // If statement to check if it is the user first time it will go to the walkthrough,if it is the 2nd time it will go to the dashboard
                if (isFirstTime){
                    SharedPreferences.Editor editor = boardingScreen.edit();
                    editor.putBoolean("firstTime", false);
                    editor.commit();


                    Intent intent = new Intent(MainActivity.this, IntroActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                }


            }
        }, SPLASH_SCREEN);

    }
}