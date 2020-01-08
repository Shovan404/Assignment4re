package com.e.hamrobazar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class AgreementActivity extends AppCompatActivity {

    CheckBox checkOne, checkTwo, checkThree;
    Button btnAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agreement);
        getSupportActionBar().hide();

        checkOne = findViewById(R.id.cbFirst);
        checkTwo = findViewById(R.id.cbSecond);
        checkThree= findViewById(R.id.cbThird);

        btnAgree= findViewById(R.id.btnAgreement);

        checkOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agreementone = new Intent(AgreementActivity.this,AgreementOneActivity.class);
                startActivity(agreementone);

            }
        });

        checkTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agreementtwo = new Intent(AgreementActivity.this,AgreementTwoActivity.class);
                startActivity(agreementtwo);

            }
        });

        checkThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agreementthree = new Intent(AgreementActivity.this,AgreementThreeActivity.class);
                startActivity(agreementthree);

            }
        });

        btnAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AgreementActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });




    }
}
