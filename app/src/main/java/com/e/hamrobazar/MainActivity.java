package com.e.hamrobazar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.e.hamrobazar.adapter.ViewPageAdapter;
import com.e.hamrobazar.api.HamrobazarApi;
import com.e.hamrobazar.model.Products;
import com.e.hamrobazar.recycler.ProductAdapter;
import com.e.hamrobazar.url.URL;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ImageView imgLogin;
    ViewPager viewPager;
    LinearLayout expandableView;
    Button btnView, btnUp;
    CardView cardView;
    RecyclerView recyclerView1, recyclerView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        viewPager = findViewById(R.id.proImgView);





        expandableView = findViewById(R.id.expandableView);
        btnView = findViewById(R.id.btnDownArrow);
        btnUp = findViewById(R.id.btnUpArrow);
        cardView = findViewById(R.id.cardView);
        imgLogin=findViewById(R.id.imgUser);
        recyclerView1=findViewById(R.id.recyclerView1);
        recyclerView2=findViewById(R.id.recyclerView2);
        ViewPageAdapter viewPageAdapter = new ViewPageAdapter(this);
        viewPager.setAdapter(viewPageAdapter);
        RecyclerPop();
        RecyclerNew();

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

    private void RecyclerPop(){
        HamrobazarApi hamrobazarApi = URL.getInstance().create(HamrobazarApi.class);
        Call<List<Products>> listCall = hamrobazarApi.getProduct();

        listCall.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Products> productsList = response.body();
                ProductAdapter productAdapter = new ProductAdapter(MainActivity.this,productsList);
                recyclerView1.setAdapter(productAdapter);
                recyclerView1.setLayoutManager(new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false));
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void RecyclerNew(){
        HamrobazarApi hamrobazarApi = URL.getInstance().create(HamrobazarApi.class);
        Call<List<Products>> listCall = hamrobazarApi.getProduct();

        listCall.enqueue(new Callback<List<Products>>() {
            @Override
            public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                    return;
                }
                List<Products> productsList = response.body();
                ProductAdapter productAdapter = new ProductAdapter(MainActivity.this,productsList);
                recyclerView2.setAdapter(productAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,LinearLayoutManager.HORIZONTAL,false);
                linearLayoutManager.setReverseLayout(true);
                linearLayoutManager.setStackFromEnd(true);

                recyclerView2.setLayoutManager(linearLayoutManager);
            }

            @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error"+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
