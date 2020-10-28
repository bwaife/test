package com.benjamin_waife.car_system;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.benjamin_waife.car_system.HelperClass.SlideAdapter;

public class IntroActivity extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dotsLayout;
    SlideAdapter slideAdapter;
    TextView[] dots;
    Button LGSbtn;
    int currentPos;
    Animation anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Remove status bar
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_intro);

        viewPager = findViewById(R.id.slider);
        dotsLayout = findViewById(R.id.dots);
        LGSbtn = findViewById(R.id.btn);

        //Call Adapter
        slideAdapter = new SlideAdapter(this);
        viewPager.setAdapter(slideAdapter);

        addDots(0);
        viewPager.addOnPageChangeListener(changeListener);

    }

    public void skip(View view){
        startActivity(new Intent(this, Dashboard.class));
        finish();

    }
    public void next(View view){
        viewPager.setCurrentItem(currentPos + 1);
    }

    private void addDots(int position) {
        dots = new TextView[4];
        dotsLayout.removeAllViews();

        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);

            dotsLayout.addView(dots[i]);

        }
        if (dots.length > 0){
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        }
    }

    ViewPager.OnPageChangeListener changeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
            currentPos = position;


            if (position == 0){
                LGSbtn.setVisibility(View.INVISIBLE);
            }
            else if (position == 1){
                LGSbtn.setVisibility(View.INVISIBLE);
            }
            else if (position == 2){
                LGSbtn.setVisibility(View.INVISIBLE);
            }
            else{
                //Load and set an animation on the Let Get Started Button
                anim = AnimationUtils.loadAnimation(IntroActivity.this, R.anim.bottom_animation);
                LGSbtn.setAnimation(anim);
                LGSbtn.setVisibility(View.VISIBLE);
            }


        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }

    };
}