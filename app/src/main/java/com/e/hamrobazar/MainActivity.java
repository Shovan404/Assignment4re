package com.e.hamrobazar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.e.hamrobazar.adapter.ViewPageAdapter;

public class MainActivity extends AppCompatActivity {

    ImageView imgLogin;
    ViewPager viewPager;
    LinearLayout expandableView;
    Button btnView, btnUp;
    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.proImgView);

        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(this);

        viewPager.setAdapter(viewPageAdapter);

        expandableView = findViewById(R.id.expandableView);
        btnView = findViewById(R.id.btnDownArrow);
        btnUp = findViewById(R.id.btnUpArrow);
        cardView = findViewById(R.id.cardView);
        imgLogin=findViewById(R.id.imgUser);

        imgLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView.getVisibility()==View.VISIBLE){
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    btnView.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    btnUp.setVisibility(View.GONE);
                    btnView.setVisibility(View.VISIBLE);
                }

            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableView.getVisibility()==View.GONE){
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.VISIBLE);
                    //btnView.setBackgroundResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                    btnUp.setVisibility(View.VISIBLE);
                    btnView.setVisibility(View.GONE);
                }
                else {
                    TransitionManager.beginDelayedTransition(cardView, new AutoTransition());
                    expandableView.setVisibility(View.GONE);
                    btnView.setBackgroundResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                    btnUp.setVisibility(View.GONE);
                }

            }
        });
    }
}
