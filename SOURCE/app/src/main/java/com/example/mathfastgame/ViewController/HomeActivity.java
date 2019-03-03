package com.example.mathfastgame.ViewController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.mathfastgame.R;
import com.example.mathfastgame.ViewController.Base.BaseActivity;

public class HomeActivity extends BaseActivity {
    ImageButton btnPlay,btnRank,btnInfor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        addEvents();
    }

    private void addEvents() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomeActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void init() {
        btnInfor=findViewById(R.id.btn_infor);
        btnPlay=findViewById(R.id.btn_play);
        btnRank=findViewById(R.id.btn_rank);
    }
}
