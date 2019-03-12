package com.example.mathfastgame.ViewController;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mathfastgame.Database.UserDataSource;
import com.example.mathfastgame.Model.User;
import com.example.mathfastgame.R;

public class GameOverActivity extends AppCompatActivity {

    TextView txtPoint,txtTop;
    ImageButton btnReplay,btnBack,btnShare;
    UserDataSource userDataSource;
    int point;
    Button btnok;
    EditText edtNameUser;
    View dialogView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);
        init();

    }

    private void init() {
        point=getIntent().getIntExtra("point",-1);
        txtPoint=findViewById(R.id.txt_point);
        txtTop=findViewById(R.id.txt_top);
        btnReplay=findViewById(R.id.btn_replay);
        btnBack=findViewById(R.id.btn_back);
        btnShare=findViewById(R.id.btn_share);
        txtPoint.setText(point+"");
        addEvents();
        dialogView= LayoutInflater.from(this).inflate(R.layout.infor_user,null);
        userDataSource=new UserDataSource(this);
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setView(dialogView);
        final AlertDialog dialog=builder.create();
        dialog.setCancelable(false);
        btnok=dialogView.findViewById(R.id.btn_ok);
        edtNameUser=dialogView.findViewById(R.id.edt_name_user);
        dialog.show();
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user=new User();
                user.setNameUser(edtNameUser.getText().toString());
                user.setPoint(point);
                if (userDataSource.insertUser(user)>0){
                    Toast.makeText(GameOverActivity.this,"thêm thành công",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }else {
                    Toast.makeText(GameOverActivity.this,"xảy ra lỗi",Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }
            }
        });

    }

    private void addEvents() {
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
