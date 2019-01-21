package com.example.huwt.oldmanassistant;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huwt.oldmanassistant.db.DbContract;
import com.example.huwt.oldmanassistant.db.UserDbHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    // todo 将登录页与注册页合并
    // UI
    private EditText mAccountEt;
    private EditText mPwdEt;

    private TextView mRegisterTv;
    private TextView mForgetPwdTv;

    private Button mLoginBtn;
    // other
    private SQLiteDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mDb = new UserDbHelper(this).getReadableDatabase();
        initView();
    }
    private void initView(){
        mAccountEt = findViewById(R.id.et_account);
        mPwdEt = findViewById(R.id.et_pwd);

        mRegisterTv = findViewById(R.id.tv_register);
            mRegisterTv.setOnClickListener(this);
        mForgetPwdTv = findViewById(R.id.tv_forget_pwd);
            mForgetPwdTv.setOnClickListener(this);

        mLoginBtn = findViewById(R.id.btn_register);
            mLoginBtn.setOnClickListener(this);
    }


    private boolean check(String userAccount, String pwd){
        Cursor cursor = mDb.query(DbContract.UserEntry.TABLE_NAME,
                new String[]{DbContract.UserEntry.COLUMN_USER_PWD},
                DbContract.UserEntry.COLUMN_USER_ACCOUNT+"=?",
                new String[]{userAccount},
                null,
                null,
                DbContract.UserEntry._ID
                );
        if(cursor.getCount() == 0){
            Toast.makeText(this, "没有此用户名, 请先注册", Toast.LENGTH_SHORT).show();
            return false;
        }
        while (cursor.moveToNext()){
            if(cursor.getString(cursor.getColumnIndex(DbContract.UserEntry.COLUMN_USER_PWD)).equals(pwd)){
                return true;
            }
        }
        Toast.makeText(this, "密码错误!", Toast.LENGTH_SHORT).show();
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                // 如果账户密码正确就进入主页面 MainActivity, 否则就弹出Toast
                if(check(mAccountEt.getText().toString(), mPwdEt.getText().toString())){
                    MainActivity.setCurrentAccount(mAccountEt.getText().toString(), this);

                    Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    LoginActivity.this.finish();
                }
                break;
            case R.id.tv_register: // 注册
                startActivity(new Intent(this, RegisterActivity.class));
                this.finish();
                break;
            case R.id.tv_forget_pwd:
                // todo P1 找回密码
                Toast.makeText(LoginActivity.this, "忘记密码.. ", Toast.LENGTH_SHORT).show();
                break;

        }
    }




}
