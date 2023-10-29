package org.myAPP.javaClass.activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.myAPP.R;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ActionBar bar = getSupportActionBar();
        bar.hide();
        TextView view = findViewById(R.id.welcomeView);
        Intent intent = getIntent();
        String account = intent.getStringExtra("userAccount");
        String userType = intent.getStringExtra("userType");
        String uid = intent.getStringExtra("uid");
        view.setText("欢迎，"+account+"！");
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(userType.equals("student")){
            fragmentTransaction.add(R.id.contentLayout,new StuNavigatorFragment());
        }else if("teacher".equals(userType)){
            fragmentTransaction.add(R.id.contentLayout,new TeacherNavigatorFragment());
        }
        fragmentTransaction.commit();

    }
}