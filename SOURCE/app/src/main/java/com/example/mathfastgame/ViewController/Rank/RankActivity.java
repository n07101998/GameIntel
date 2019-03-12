package com.example.mathfastgame.ViewController.Rank;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.example.mathfastgame.Database.UserDataSource;
import com.example.mathfastgame.Model.User;
import com.example.mathfastgame.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class RankActivity extends AppCompatActivity {
    ListView lvRank;
    RankAdapter adapter;
    ArrayList<User> arrUser;
    UserDataSource userDataSource;
    View headerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);
        init();
    }

    private void init() {
        headerView= LayoutInflater.from(this).inflate(R.layout.header_rank,null);
        userDataSource = new UserDataSource(this);
        lvRank = findViewById(R.id.lv_rank);
        lvRank.addHeaderView(headerView);
        arrUser = userDataSource.getListUser();
        Collections.sort(arrUser, new Comparator<User>() {
            @Override
            public int compare(User o1, User o2) {
                if (o1.getPoint()>o2.getPoint()){
                    return -1;
                }else if (o1.getPoint()<o2.getPoint()){
                    return 1;
                }
                return 0;
            }
        });
        adapter = new RankAdapter(arrUser, this);
        lvRank.setAdapter(adapter);

//        adapter.notifyDataSetChanged();
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void btnOK(View view) {
        finish();
    }
}
