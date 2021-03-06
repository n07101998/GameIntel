package com.example.mathfastgame.ViewController;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.example.mathfastgame.Database.AssetDatabaseOpenHelper;
import com.example.mathfastgame.Database.GameDataSoucre;
import com.example.mathfastgame.Model.Game;
import com.example.mathfastgame.R;
import com.example.mathfastgame.ViewController.Base.BaseActivity;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {
    ProgressBar pbCount;
    TextSwitcher txtQues;
    TextView txtPoint;
    ImageButton btnTrue, btnFalse;
    ArrayList<Game> arrData;
    Random random = new Random();
    int point = 0;
    int pos;
    int rangeRandom=50;
    int count=10;
    Timer timer;
    boolean isGameOver=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setUpView();
    }

    @Override
    protected void onResume() {
        super.onResume();
        isGameOver=false;
    }

    private void setUpView() {
        txtQues.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(MainActivity.this);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(70);
                textView.setGravity(Gravity.CENTER);
                return textView;
            }
        });
        GameDataSoucre gameDataSoucre = new GameDataSoucre(this);
        arrData = gameDataSoucre.getAllGameData();
        pos = random.nextInt(rangeRandom);
        txtQues.setText(arrData.get(pos).getQues());
    }

    private void init() {
        pbCount = findViewById(R.id.pb_count);
        txtQues = findViewById(R.id.txt_ques);
        txtPoint = findViewById(R.id.txt_point);
        btnFalse = findViewById(R.id.btn_false);
        btnTrue = findViewById(R.id.btn_true);
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processLogic("1");
            }
        });
        btnFalse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               processLogic("0");
            }
        });
    }

    private void plusPoint() {
        point++;
        txtPoint.setText(point + "");
    }
    void processLogic(String answer){
        if (arrData.get(pos).getAnswer().trim().equals(answer)) {
            if(timer != null) {
                timer.cancel();
                timer = null;
            }
            count=11;
            processCountDown();
            plusPoint();
            pos = random.nextInt(rangeRandom);
            txtQues.setText(arrData.get(pos).getQues());
        }else{
            isGameOver=true;
            processGameOver();
        }
    }

    private void processGameOver() {
        Intent intent=new Intent(MainActivity.this,GameOverActivity.class);
        intent.putExtra("point",point);
        startActivity(intent);
        finish();
    }

    void processCountDown(){
        TimerTask timerTask=new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        count--;
                        pbCount.setProgress(count);
                        if (count==0 && !isGameOver){
                            processGameOver();
                            isGameOver=true;
                        }
                    }
                });
            }
        };
        timer=new Timer();
        timer.schedule(timerTask,0,150);
    }
}
