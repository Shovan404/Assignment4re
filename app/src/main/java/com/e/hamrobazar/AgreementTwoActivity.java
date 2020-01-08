package com.e.hamrobazar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class AgreementTwoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement_two);

        getSupportActionBar().setTitle("Safety Tips for Transcation");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
