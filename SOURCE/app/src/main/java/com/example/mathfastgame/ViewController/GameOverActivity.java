package com.example.mathfastgame.ViewController;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mathfastgame.R;

public class GameOverActivity extends AppCompatActivity {

    TextView txtPoint,txtTop;
    ImageButton btnReplay,btnBack,btnShare;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        init();
    }

    private void init() {
        txtPoint=findViewById(R.id.txt_point);
        txtTop=findViewById(R.id.txt_top);
        btnReplay=findViewById(R.id.btn_replay);
        btnBack=findViewById(R.id.btn_back);
        btnShare=findViewById(R.id.btn_share);

        txtPoint.setText(getIntent().getIntExtra("point",-1)+"");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GameOverActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GameOverActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
