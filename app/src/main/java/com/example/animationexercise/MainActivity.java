package com.example.animationexercise;


import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {

    ObjectAnimator mAnimator, Test, FadeIn;
    Button left,right,getar,menghilang,menggoda;
//    RelativeLayout relativeLayout;  //declare this globally

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imgView = findViewById(R.id.img);
        Button btn = findViewById(R.id.btn_start);

        mAnimator = ObjectAnimator.ofFloat(imgView, "rotation", 0f, 360f);
        Test = ObjectAnimator.ofFloat(imgView,"alpha", 1);
        FadeIn = ObjectAnimator.ofFloat(imgView, View.ALPHA, 0,1);
        imgView.setVisibility(View.GONE);

        left = findViewById(R.id.btnLeft);
        right = findViewById(R.id.btnRight);
        getar = findViewById(R.id.btnGetar);
        menghilang = findViewById(R.id.btnIlang);
        menggoda = findViewById(R.id.btnGoda);

//        goLeft = ObjectAnimator.ofFloat(imgView, "",)

        getar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Wave).duration(1000).repeat(0).playOn(imgView);
            }
        });

        menghilang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.FadeOutUp).duration(1000).repeat(0).playOn(imgView);
            }
        });

        menggoda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.Flash).duration(1000).repeat(0).playOn(imgView);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imgView.setVisibility(View.VISIBLE);
                mAnimator.setDuration(1000);
                Test.setDuration(1000);
                FadeIn.start();
                Test.start();
                mAnimator.start();

                imgView.clearAnimation();
                TranslateAnimation transAnim = new TranslateAnimation(0, 0, 0,
                        getDisplayHeight()/2);
                transAnim.setStartOffset(500);
                //Durasi Jatuh
                transAnim.setDuration(8000);
                transAnim.setFillAfter(true);
                transAnim.setInterpolator(new BounceInterpolator());
                transAnim.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {
                        Log.i(TAG, "Starting button dropdown animation");

                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        // TODO Auto-generated method stub

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        Log.i(TAG,
                                "Ending button dropdown animation. Clearing animation and setting layout");
//                        imgView.clearAnimation();
//                        final int left = imgView.getLeft();
//                        final int top = imgView.getTop();
//                        final int right = imgView.getRight();
//                        final int bottom = imgView.getBottom();
//                        imgView.layout(left, top, right, bottom);

                    }
                });
                imgView.startAnimation(transAnim);
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.BounceInLeft).duration(1000).repeat(0).playOn(imgView);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                YoYo.with(Techniques.BounceInRight).duration(1000).repeat(0).playOn(imgView);
            }
        });
    }
    private int getDisplayHeight() {
        return this.getResources().getDisplayMetrics().heightPixels;
    }
}