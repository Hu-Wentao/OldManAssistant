package com.example.huwt.oldmanassistant;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huwt.oldmanassistant.main.fragment.ChildFragment;
import com.example.huwt.oldmanassistant.main.fragment.CircleFragment;
import com.example.huwt.oldmanassistant.main.fragment.PushFragment;
import com.example.huwt.oldmanassistant.main.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {

    // 应用常量
    public static final String USER_INFO = "user_info"; // 作为SharedPreference的文件名
    public static final String CURRENT_ACCOUNT = "current_account"; // 当前用户名的 key
    public static final String LOGIN_STATUS = "login_Status";   // 是否登录的key

    // navigationView
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    // fragment
    private FragmentTransaction fragmentTransaction;
//    private FragmentManager fragmentManager;

    private SearchFragment searchFragment;  // 一键搜索
    private ChildFragment childFragment;    // 子女关联
    private PushFragment pushFragment;      // 生活推送
    private CircleFragment circleFragment;  // 活动圈
    // other
    private BottomNavigationView bnv;
    private TextView tvTitleBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initBottomNavigationBar();
        initNavigationView();

    }
    @Override
    protected void onResume(){
        super.onResume();
        // 为该按钮动态设置标题
        navigationView.getMenu().findItem(R.id.menu_sign_out)
                .setTitle(getLoggingStatus(this)?this.getString(R.string.menu_logout):this.getString(R.string.menu_login_or_register));
    }

    /** 初始化控件 */
    private void initView(){
        tvTitleBar = findViewById(R.id.tv_title_bar);

    }

    /** 初始化底部导航栏 */
    private void initBottomNavigationBar(){
        // 启用碎片
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager
                .beginTransaction();    // ft是 FragmentTransaction 的实例
        // 初始化Fragment
        searchFragment = new SearchFragment();

        // 将创建的fragment添加到Activity布局文件中定义的占位符中（FrameLayout）
//        fragmentTransaction.add(R.id.main_fragment_container, searchFragment);
        //设置默认碎片
        tvTitleBar.setText(R.string.navigation_search);
        fragmentTransaction
                .replace(R.id.main_fragment_container, searchFragment)
                .commit();

        // 找 BottomNavigationView
        bnv = findViewById(R.id.main_bnv);
        bnv.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                        fragmentTransaction = getSupportFragmentManager().beginTransaction(); // 启用碎片
                        switch (menuItem.getItemId()) {
                            case R.id.navigation_search:
                                tvTitleBar.setText(R.string.navigation_search);
                                if(searchFragment == null){
                                    searchFragment = new SearchFragment();
                                }
                                fragmentTransaction.replace(R.id.main_fragment_container, searchFragment);
                                break;
                            case R.id.navigation_child:
                                tvTitleBar.setText(R.string.navigation_child);
                                if(childFragment == null){
                                    childFragment = new ChildFragment();
                                }
                                fragmentTransaction.replace(R.id.main_fragment_container, childFragment);
                                break;
                            case R.id.navigation_push:
                                tvTitleBar.setText(R.string.navigation_push);
                                if(pushFragment == null){
                                    pushFragment = new PushFragment();
                                }
                                fragmentTransaction.replace(R.id.main_fragment_container, pushFragment);
                                break;
                            case R.id.navigation_circle:
                                tvTitleBar.setText(R.string.navigation_circle);
                                if(circleFragment == null){
                                    circleFragment = new CircleFragment();
                                }
                                fragmentTransaction.replace(R.id.main_fragment_container, circleFragment);
                                break;
                            default:
                                throw new RuntimeException("onNavigationItemSelected: 出现了没有被指定功能的Navigation menuItem");
                        }
                        fragmentTransaction.commit();   // 事务结束
                        return true;
                    }
                }
        );
    }

    /** 初始化左侧菜单 */
    private void initNavigationView(){
        navigationView = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);


        navigationView.getHeaderView(0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(navigationView);
                Toast.makeText(MainActivity.this, "Header View is clicked!", Toast.LENGTH_SHORT).show();
            }
        });
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_user_info:
                        //todo P5 展示当前用户登录信息
                        Toast.makeText(MainActivity.this, "user_info is clicked!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_settings:
                        // todo P1 做一些偏好设置, 使用 PreferenceFragment
                        Toast.makeText(MainActivity.this, "Settings is clicked!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_sign_out:    // 退出登录
                        setLoggingStatus(false, MainActivity.this);
                        Log.d("MainActivity", "已注销");
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));  // 启动
                        break;
                    case R.id.menu_about:
                        // todo P3 写一个关于页面
                        Toast.makeText(MainActivity.this, "About is clicked!", Toast.LENGTH_SHORT).show();
                        break;
                }
                drawerLayout.closeDrawer(navigationView);
                return false;
            }
        });

    }

    public static boolean setLoggingStatus(boolean b, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return sharedPreferences.edit()
                .putBoolean(LOGIN_STATUS, b)
                .commit();
    }
    public static boolean getLoggingStatus(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean(LOGIN_STATUS, false)){  // 默认为未登录
            if(getCurrentAccount(context) == null){ // 如果返回已登录, 但是当前账户为null, 则仍为 未登录
                setLoggingStatus(false, context);
                return false;
            }else {
                return true;
            }
        } else {
            return false;
        }
    }
    /** 设置当前的用户名, (如果用户成功登陆的话) */
    public static boolean setCurrentAccount(String userAccount, Context context){
        SharedPreferences sharePreference = context.getApplicationContext().getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);

        setLoggingStatus(true, context);
        return sharePreference.edit()
                .putString(CURRENT_ACCOUNT, userAccount)
                .commit();
    }
    /** 获取当前的用户名, 如果为null, 则表示当前没有登陆 */
    public static String getCurrentAccount(Context context){
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences(USER_INFO, Context.MODE_PRIVATE);
        return sharedPreferences.getString(CURRENT_ACCOUNT, null);
    }

}
