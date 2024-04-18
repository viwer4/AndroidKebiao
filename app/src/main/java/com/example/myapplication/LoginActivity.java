package com.example.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.UserDbHelper;
import com.example.myapplication.entity.UserInfo;

public class LoginActivity extends AppCompatActivity{

    // 声明控件
    private EditText et_username;
    private EditText et_password;
    private SharedPreferences mShareActionProvider;
    private CheckBox checkBox;

    private boolean is_login;


//    private Button mBtnLogin;
//    private EditText mEtUser;
//    private EditText mEtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 初始化控件
        et_username = findViewById(R.id.et_username);
        et_password = findViewById(R.id.et_password);
        checkBox = findViewById(R.id.checkbox);

        //获取mShareActionProvider
        mShareActionProvider = getSharedPreferences("user", MODE_PRIVATE);

        //是否勾选了记住密码
        is_login = mShareActionProvider.getBoolean("is_login", false);
        if (is_login) {
            String username = mShareActionProvider.getString("username", null);
            String password = mShareActionProvider.getString("password", null);
            et_username.setText(username);
            et_password.setText(password);
            checkBox.setChecked(true);

        }

        //点击注册
        findViewById(R.id.btn_Register).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转注册页面
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);

            }
        });


        // 找到控件
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = et_username.getText().toString();
                String password = et_password.getText().toString();
                if (TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
                    Toast.makeText(LoginActivity.this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                } else {
                    UserInfo login = UserDbHelper.getInstance(LoginActivity.this).login(username);
                    if (login != null) {
                        if (username.equals(login.getUsername()) && password.equals(login.getPassword())) {
                            //是否勾选里记住密码
                            SharedPreferences.Editor edit = mShareActionProvider.edit();
                            edit.putBoolean("is_login", is_login);
                            edit.putString("username", username);
                            edit.putString("password", password);
                            //提交
                            edit.commit();

                            //保存用户名和密码
                            UserInfo.setUserInfo(login);

                            //登入成功
                            Intent intent = new Intent(LoginActivity.this, FunctionActivity.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Toast.makeText(LoginActivity.this, "用户名和密码错误", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "该账户未注册", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        //checkBox 点击事件
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                is_login = isChecked;
            }
        });
    }
}