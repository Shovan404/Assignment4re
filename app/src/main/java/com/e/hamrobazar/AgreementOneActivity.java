package com.e.hamrobazar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AgreementOneActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement_one);
        getSupportActionBar().setTitle("Terms & Condition for Use");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
