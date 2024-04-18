package com.example.myapplication;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.db.UserDbHelper;
import com.example.myapplication.entity.UserInfo;

public class UpdatePwdActivity extends AppCompatActivity {
    private EditText et_new_password;
    private EditText et_confirm_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_pwd);

        //舒适化空间
        et_new_password = findViewById(R.id.et_new_password);
        et_confirm_password = findViewById(R.id.et_confirm_password);

        //修改密码点击事件
        findViewById(R.id.btn_update_pwd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String new_pwd = et_new_password.getText().toString();
                String new_confirm = et_confirm_password.getText().toString();

                if (TextUtils.isEmpty(new_pwd) || TextUtils.isEmpty(new_confirm)) {
                    Toast.makeText(UpdatePwdActivity.this, "信息不能为空", Toast.LENGTH_SHORT).show();
                } else if (!new_pwd.equals(new_confirm)) {
                    Toast.makeText(UpdatePwdActivity.this, "新密码和旧密码不一致", Toast.LENGTH_SHORT).show();
                } else {

                    UserInfo userInfo = UserInfo.getUserInfo();
                    if (null != userInfo) {
                        int row = UserDbHelper.getInstance(UpdatePwdActivity.this).updatePwd(userInfo.getUsername(), new_pwd);
                        if (row > 0) {
                            Toast.makeText(UpdatePwdActivity.this, "密码修改成功，请重新登入", Toast.LENGTH_SHORT).show();
                            setResult(1000);
                            finish();
                        } else {
                            Toast.makeText(UpdatePwdActivity.this, "修改失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
        //返回
        findViewById(R.id.toolbar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}