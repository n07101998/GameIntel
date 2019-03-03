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

public class MainActivity extends BaseActivity {
    ProgressBar pbCount;
    TextSwitcher txtQues;
    TextView txtPoint;
    ImageButton btnTrue, btnFalse;
    ArrayList<Game> arrData;
    Random random = new Random();
    int point = 0;
    int pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        setUpView();
    }

    private void setUpView() {
        txtQues.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                TextView textView = new TextView(MainActivity.this);
                textView.setTextColor(Color.WHITE);
                textView.setTextSize(70);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                return textView;
            }
        });
        AssetDatabaseOpenHelper db = new AssetDatabaseOpenHelper(this);
        db.processCopy();
        GameDataSoucre gameDataSoucre = new GameDataSoucre(this);
        arrData = gameDataSoucre.getAllGameData();

        pos = random.nextInt(17);
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
            plusPoint();
            pos = random.nextInt(17);
            txtQues.setText(arrData.get(pos).getQues());
        }else {
            Intent intent=new Intent(MainActivity.this,GameOverActivity.class);
            intent.putExtra("point",point);
            startActivity(intent);
            finish();
        }
    }
}
