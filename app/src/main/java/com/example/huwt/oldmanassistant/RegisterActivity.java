package com.example.huwt.oldmanassistant;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huwt.oldmanassistant.db.DbContract;
import com.example.huwt.oldmanassistant.db.UserDbHelper;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {
                                                        // todo P3 添加一个返回 登录页 的按钮
    //UI                                               // todo P1 在信息输入View右侧 添加信息输入错误的提示控件
    private EditText mAccountEt;    // 账号            // todo P2 用户名查重, 防止重复注册
    private EditText mPwdEt;        // 密码
    private EditText mConfirmPwdEt; // 重复密码
    private Switch mSexSwitch;      // 性别
    private Switch mUserTypeSwitch; // 用户类型(老人/子女)
    private EditText mBirthdayEt;   // 出生日期         // todo P2 改用日期选择器
    private EditText mUserPhone;    // 手机号
    private EditText mAddress;      // 家庭住址

    private Button mRegisterBtn;    // 注册按钮

    private TextView mShowErrorInfoTv; // 显示出错信息

    // 其他
    private SQLiteDatabase mDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        UserDbHelper userDbHelper = new UserDbHelper(this);
        mDb = userDbHelper.getWritableDatabase();
        initView();
    }
    private void initView(){
        mShowErrorInfoTv = findViewById(R.id.tv_show_error);

        mAccountEt = findViewById(R.id.et_account);
        mPwdEt = findViewById(R.id.et_pwd);
        mConfirmPwdEt = findViewById(R.id.et_confirm_pwd);
            mConfirmPwdEt.setOnFocusChangeListener(this);
        mSexSwitch = findViewById(R.id.switch_sex);
        mUserTypeSwitch = findViewById(R.id.switch_user_type);
        mBirthdayEt = findViewById(R.id.et_user_birthday);
            mBirthdayEt.setOnFocusChangeListener(this);
        mUserPhone = findViewById(R.id.et_user_phone);
        mAddress = findViewById(R.id.et_user_address);

        mRegisterBtn = findViewById(R.id.btn_register);
            mRegisterBtn.setOnClickListener(this);
    }

    /** 验证两次输入的密码是否一致 */
    private boolean checkPassword(){
        String pwd1 = mPwdEt.getText().toString();
        String pwd2 = mConfirmPwdEt.getText().toString();

        if(!pwd1.equals(pwd2)){
            showErr("两次密码不一致! 请重设密码");
            return false;
        }
        return true;
    }

    /** 验证出生年月是否符合要求 */     // todo 年份合法性验证,可改用日期选择器
    private boolean checkBirthday(){
        String day = mBirthdayEt.getText().toString();
        if(Pattern.matches(" ^(^(\\d{4}|\\d{2})(\\-|\\/|\\.)\\d{1,2}\\3\\d{1,2}$)|(^\\d{4}年\\d{1,2}月\\d{1,2}日$)$", day)){
            return true;
        }
        showErr("出生日期格式不正确");
        return false;
    }
    /** 用户名查重 */
    private boolean checkAccount(){
        // todo
        return true;
    }

    /** 操作 mShowErrorInfoTv 的方法 */
    private void showErr(String errInfo){
        mShowErrorInfoTv.setVisibility(View.VISIBLE);

        Toast.makeText(this, errInfo, Toast.LENGTH_SHORT).show();
        mShowErrorInfoTv.append(errInfo+"\n");
    }
    private void cancelErr(){
        mShowErrorInfoTv.setText("");    // 清空错误信息
        mShowErrorInfoTv.setVisibility(View.GONE);  // 设为 隐藏
    }



    /** 将新注册的用户信息写入数据库 */
    private long addNewUser(String userAccount, String pwd, boolean sex, boolean userType, String birthday, String phone, String address){
        ContentValues cv = new ContentValues();
        cv.put(DbContract.UserEntry.COLUMN_USER_ACCOUNT, userAccount);
        cv.put(DbContract.UserEntry.COLUMN_USER_PWD, pwd);
        cv.put(DbContract.UserEntry.COLUMN_USER_SEX, sex);
        cv.put(DbContract.UserEntry.COLUMN_USER_TYPE, userType);
        cv.put(DbContract.UserEntry.COLUMN_USER_BIRTHDAY, birthday);
        cv.put(DbContract.UserEntry.COLUMN_USER_PHONE, phone);
        cv.put(DbContract.UserEntry.COLUMN_USER_ADDRESS, address);
        return mDb.insert(DbContract.UserEntry.TABLE_NAME, null,cv);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_register:
                cancelErr();
                if(checkPassword() && checkBirthday() && checkAccount()){
                addNewUser(mAccountEt.getText().toString(),
                        mPwdEt.getText().toString(),
                        !mSexSwitch.isChecked(),        // 真男假女, 此处Switch开关是反的
                        !mUserTypeSwitch.isChecked(),   // 真老假小, 此处Switch开关是反的
                        mBirthdayEt.getText().toString(),
                        mUserPhone.getText().toString(),
                        mAddress.getText().toString()
                        );
                if(!MainActivity.setCurrentAccount(mAccountEt.getText().toString(), this)){     // 存放用户登录信息
                    showErr("无法写入注册信息到本地, 请检查存储权限");
                }
                if (BuildConfig.DEBUG) Log.d("RegisterActivity", "已登录, 并且为登录状态");
                this.finish();  // 结束当前Activity
                }
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus)  // 失去焦点时执行 switch
        switch (v.getId()){
            case R.id.et_account:
                checkAccount();
                break;
            case R.id.et_confirm_pwd:
                checkPassword();
                break;
            case R.id.et_user_birthday:
                checkBirthday();
                break;
        }
    }
}
