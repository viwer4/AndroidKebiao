package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ShareActionProvider;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.example.myapplication.db.UserDbHelper;

import org.w3c.dom.Text;

public class RegisterActivity extends AppCompatActivity{


    private EditText et_username;
    private EditText et_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);


        // 初始化控件
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);

        //返回
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //返回注册页面 —— 注销当前页面
                finish();
            }
        });


        //点击注册
        findViewById(R.id.Register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();

                if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
                    Toast.makeText(RegisterActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                } else {

                    int row = UserDbHelper.getInstance(RegisterActivity.this).register(username, password, "这个人很懒，什么也没留下~");
                    if (row > 0) {
                        Toast.makeText(RegisterActivity.this, "注册成功 请登入", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }
            }
        });


    }


}