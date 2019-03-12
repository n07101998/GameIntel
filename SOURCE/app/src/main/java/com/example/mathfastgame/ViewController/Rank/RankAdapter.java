package com.example.mathfastgame.ViewController.Rank;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mathfastgame.Model.User;
import com.example.mathfastgame.R;

import java.util.ArrayList;

public class RankAdapter extends BaseAdapter {
    ArrayList<User> arrData;
    Context context;

    public RankAdapter(ArrayList<User> arrData, Context context) {
        this.arrData = arrData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.item_rank,parent,false);
            viewHolder=new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        User user=arrData.get(position);
        viewHolder.setUpView(user,position+1);
        return convertView;
    }
    class ViewHolder{
        TextView txtSTT,txtNameUser,txtPoint;

        public ViewHolder(View view) {
            txtSTT=view.findViewById(R.id.txt_stt);
            txtNameUser=view.findViewById(R.id.txt_name_user);
            txtPoint=view.findViewById(R.id.txt_point);
        }
        public void setUpView(User user,int pos){
            txtSTT.setText(pos+"");
            txtNameUser.setText(user.getNameUser());
            txtPoint.setText(user.getPoint()+"");
        }
    }
}
