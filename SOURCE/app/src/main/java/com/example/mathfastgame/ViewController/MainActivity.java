package com.example.mathfastgame.ViewController;

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

import com.example.mathfastgame.R;
import com.example.mathfastgame.ViewController.Base.BaseActivity;

public class MainActivity extends BaseActivity {
    ProgressBar pbCount;
    TextSwitcher txtQues;
    TextView txtPoint;
    ImageButton btnTrue,btnFalse;

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
                textView.setTextSize(100);
                textView.setGravity(Gravity.CENTER_HORIZONTAL);
                return textView;
            }
        });
        txtQues.setText("2+5=4");

    }
    int i=0;
    private void init() {
        pbCount=findViewById(R.id.pb_count);
        txtQues=findViewById(R.id.txt_ques);
        txtPoint=findViewById(R.id.txt_point);
        btnFalse=findViewById(R.id.btn_false);
        btnTrue=findViewById(R.id.btn_true);
        btnTrue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ++i;
                txtQues.setText("1+1="+i);
            }
        });
    }
}
