package org.myAPP.javaClass.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.myAPP.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        class clickButton implements View.OnClickListener{

            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }

        Button stuBtu=findViewById(R.id.stuLogin);
        stuBtu.setOnClickListener(new clickButton());
        Button teacherBtu = findViewById(R.id.teacherLogin);
        teacherBtu.setOnClickListener(new clickButton());
    }
}