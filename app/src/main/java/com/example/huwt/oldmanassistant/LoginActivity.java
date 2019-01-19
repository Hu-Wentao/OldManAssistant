package com.example.huwt.oldmanassistant;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.huwt.oldmanassistant.db.User;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        findViewById(R.id.btn_login).setOnClickListener(this);
        findViewById(R.id.tv_register).setOnClickListener(this);
        findViewById(R.id.tv_forget_pwd).setOnClickListener(this);

        Log.v(TAG, "进入Login !========================");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_login:
                // 如果账户密码正确就进入主页面 MainActivity, 否则就弹出Toast
                String account = ((EditText)findViewById(R.id.et_account)).getText().toString();
                String pwd = ((EditText)findViewById(R.id.et_pwd)).getText().toString();

//                Toast.makeText(LoginActivity.this, "账号为"+account+"\n密码为"+pwd , Toast.LENGTH_LONG).show(); //测试代码
                if(User.checkAccountAndPwd(account, pwd)){
                    User.isLogin = true;
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    LoginActivity.this.finish();
                }else{
                    Toast.makeText(LoginActivity.this, "账号或密码错误! ", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_register:
                // 注册
                Toast.makeText(LoginActivity.this, "注册.. ", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_forget_pwd:
                // 如果忘记密码
                Toast.makeText(LoginActivity.this, "忘记密码.. ", Toast.LENGTH_SHORT).show();
                break;

        }
    }




}
