package org.myAPP.javaClass.activity;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import org.myAPP.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

public class LoginActivity extends AppCompatActivity {
    private String name;
    private String userType;
    private String uid;
    private CountDownLatch latch = new CountDownLatch(1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextView accV=findViewById(R.id.acnt);
        TextView pwdV=findViewById(R.id.pwd);
        ActionBar bar = getSupportActionBar();
        bar.hide();
        Button btn = findViewById(R.id.login);
        btn.setOnClickListener(view -> {
            String acc=accV.getEditableText().toString();
            String pwd= String.valueOf(pwdV.getEditableText());

            send(acc,pwd);
            try {
                latch.await();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(name==null){
                show();
            }else {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext() , MenuActivity.class);
                intent.putExtra("uid",uid);
                intent.putExtra("userType",userType);
                intent.putExtra("userAccount",name);
                startActivity(intent);
                this.finish();
            }
            //测试用片段
            /*Intent intent = new Intent();
            intent.setClass(getApplicationContext() , MenuActivity.class);
            intent.putExtra("userType","teacher");
            intent.putExtra("userAccount","王小明");
            intent.putExtra("uid","00000001");
            startActivity(intent);
            this.finish();*/
        });

    }

    private void send(String id, String password) {
        //开启线程，发送请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("http://10.0.2.2:8080/login?id="+id+"&password="+password);
                    connection = (HttpURLConnection) url.openConnection();
                    //设置请求方法
                    connection.setRequestMethod("GET");
                    //设置连接超时时间（毫秒）
                    connection.setConnectTimeout(5000);
                    //设置读取超时时间（毫秒）
                    connection.setReadTimeout(5000);

                    //返回输入流
                    InputStream in = connection.getInputStream();

                    //读取输入流
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    Gson gson = new Gson();
                    Map<String,String> params = gson.fromJson(String.valueOf(result),HashMap.class);
                    name = params.get("name");
                    userType = params.get("userType");
                    uid=params.get("uid");
                    latch.countDown();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (ProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    if (connection != null) {//关闭连接
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void show() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                    TextView textView = findViewById(R.id.resultV);
                    textView.setText("登录失败！请重新尝试");
                    latch=new CountDownLatch(1);
            }
        });
    }


}