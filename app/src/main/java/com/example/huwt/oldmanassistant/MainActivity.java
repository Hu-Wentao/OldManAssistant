package com.example.huwt.oldmanassistant;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huwt.oldmanassistant.main.fragment.ChildFragment;
import com.example.huwt.oldmanassistant.main.fragment.CircleFragment;
import com.example.huwt.oldmanassistant.main.fragment.PushFragment;
import com.example.huwt.oldmanassistant.main.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";
    // navigationView
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    // fragment
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

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

        // TODO 如果尚未登录, 就打开 LoginActivity
//        if (!User.isLogin) {
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            MainActivity.this.finish();
//        }
    }

    /**
     * 初始化控件
     */
    private void initView(){
        // 初始化控件
        tvTitleBar = findViewById(R.id.tv_title_bar);

    }

    /**
     * 初始化底部导航栏
     */
    private void initBottomNavigationBar(){
        // 启用碎片
        fragmentManager = getSupportFragmentManager();
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
                    case R.id.menu_home:
                        Toast.makeText(MainActivity.this, "Home is clicked!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_settings:
                        Toast.makeText(MainActivity.this, "Settings is clicked!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_sign_out:    // 退出登录
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));  // 启动

                        break;
                    case R.id.menu_about:
                        Toast.makeText(MainActivity.this, "About is clicked!", Toast.LENGTH_SHORT).show();
                        break;
                }
                drawerLayout.closeDrawer(navigationView);
                return false;
            }
        });

    }

    @Override
    public void  onResumeFragments(){

    }


// todo 准备弃用此菜单
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.menu_logout:
//                User.isLogin = false;
//                Toast.makeText(this, "@string/menu/logout", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(MainActivity.this, LoginActivity.class));
////                MainActivity.this.finish();
//                break;
//            default:
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }


}
