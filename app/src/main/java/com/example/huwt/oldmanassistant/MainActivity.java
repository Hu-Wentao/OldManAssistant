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

import com.example.huwt.oldmanassistant.main.fragment.ChildFragment;
import com.example.huwt.oldmanassistant.main.fragment.CircleFragment;
import com.example.huwt.oldmanassistant.main.fragment.PushFragment;
import com.example.huwt.oldmanassistant.main.fragment.SearchFragment;

public class MainActivity extends AppCompatActivity implements
//        View.OnClickListener,
        SearchFragment.OnFragmentInteractionListener,
        ChildFragment.OnFragmentInteractionListener,
        PushFragment.OnFragmentInteractionListener,
        CircleFragment.OnFragmentInteractionListener {
    private static final String TAG = "Main";
    // fragment
    private FragmentTransaction fragmentTransaction;
    private FragmentManager fragmentManager;

    private SearchFragment searchFragment;
    private ChildFragment childFragment;
    private PushFragment pushFragment;
    private CircleFragment circleFragment;
    // other
    private BottomNavigationView bnv;
    private TextView tvTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        // 如果尚未登录, 就打开 LoginActivity
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
                                Log.e(TAG, "onNavigationItemSelected: 出现了没有被指定功能的Navigation menuItem");
                        }
                        fragmentTransaction.commit();
                        return true;
                    }
                }
        );
    }



    @Override
    public void  onResumeFragments(){
//        fragmentTransaction.commit();
    }

    /**
     * 填充菜单选项
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the main; this adds items to the action bar if it is present.
        //填充选项菜单（读取XML文件、解析、加载到Menu组件上）
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * 重写OptionsItemSelected(MenuItem item)来响应菜单项(MenuItem)的点击事件（根据id来区分是哪个item）
     * @param item  菜单的点击事件
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.menu_logout:
                User.isLogin = false;
                Toast.makeText(this, "@string/menu/logout", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                MainActivity.this.finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 此方法主要作用是从fragment向activity传递数据
     * @param uri
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

    }

}
