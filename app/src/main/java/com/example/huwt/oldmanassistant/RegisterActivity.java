package com.example.huwt.oldmanassistant;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.huwt.oldmanassistant.db.DbContract;
import com.example.huwt.oldmanassistant.db.User;
import com.example.huwt.oldmanassistant.db.UserDbHelper;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    //UI
   private EditText mAccountEt;    // 账号            // todo 用户名查重
    private EditText mPwdEt;        // 密码
    private EditText mConfirmPwdEt; // 重复密码
    private Switch mSexSwitch;      // 性别
    private Switch mUserTypeSwitch; // 用户类型(老人/子女)
    private EditText mBirthdayEt;   // 出生日期         // todo 改用日期选择器
    private EditText mUserPhone;    // 手机号
    private EditText mAddress;      // 家庭住址

    private Button mRegisterBtn;    // 注册按钮

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
            Toast.makeText(this, "两次密码不一致! 请重设密码", Toast.LENGTH_SHORT).show();
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
        Toast.makeText(this, "出生日期格式不正确!", Toast.LENGTH_SHORT).show();
        return false;
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
                if(checkPassword() && checkBirthday()){
                addNewUser(mAccountEt.getText().toString(),
                        mPwdEt.getText().toString(),
                        !mSexSwitch.isChecked(),        // 真男假女, 此处Switch开关是反的
                        !mUserTypeSwitch.isChecked(),   // 真老假小, 此处Switch开关是反的
                        mBirthdayEt.getText().toString(),
                        mUserPhone.getText().toString(),
                        mAddress.getText().toString()
                        );}
                break;
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus)  // 失去焦点时执行 switch
        switch (v.getId()){
            case R.id.et_confirm_pwd:
                checkPassword();
                break;
            case R.id.et_user_birthday:
                checkBirthday();
                break;
        }
    }
}
