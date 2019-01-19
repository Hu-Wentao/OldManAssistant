package com.example.huwt.oldmanassistant;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huwt.oldmanassistant.db.User;
import com.example.huwt.oldmanassistant.main.fragment.ChildFragment;
import com.example.huwt.oldmanassistant.main.fragment.CircleFragment;
import com.example.huwt.oldmanassistant.main.fragment.PushFragment;
import com.example.huwt.oldmanassistant.main.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main";
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
        // TODO 如果尚未登录, 就打开 LoginActivity
//        if (!User.isLogin) {
//            startActivity(new Intent(MainActivity.this, LoginActivity.class));
//            MainActivity.this.finish();
//        }
    }
    
    private void initView(){
        // 初始化控件
        tvTitleBar = findViewById(R.id.tv_title_bar);
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


    @Override
    public void  onResumeFragments(){

    }

// TODO 准备弃用此菜单
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        //填充选项菜单（读取XML文件、解析、加载到Menu组件上）
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

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
