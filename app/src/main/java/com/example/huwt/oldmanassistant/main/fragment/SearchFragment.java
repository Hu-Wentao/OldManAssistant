package com.example.huwt.oldmanassistant.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huwt.oldmanassistant.R;


public class SearchFragment extends Fragment {
    private View view;

    private TextView mGuidTextView;
    public SearchFragment() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search, container, false);

        init();
        return view;
    }

    private void init(){
        mGuidTextView = view.findViewById(R.id.tv_guid_info);

        mGuidTextView.append("\n\n由于时间仓促, 目前已完成的功能可通过以下操作演示\n" +
                "\n***** 注意 ***** " +
                "\n 为了保证功能测试的顺利进行, 可以本页面内容用相机拍摄暂存 \n\n" +
                "\n1. 侧滑菜单:  在本页面沿左边缘向右滑动, 打开侧边菜单\n" +
                "\n2. 用户登录:  在侧边菜单中, (如果尚未登录选择 登录/注册, 否则 选择 注销), 以进入登录页面\n" +
                "\n3. 用户注册;  在登录页面中, 选择 注册账号, 可注册任意数量可用于登录的账户\n" +
                "\t3.1 注册信息输入错误: 将展示错误详情\n" +
                "\n4. TodoList:  左右滑动移除Todo项\n" +
                "\n5. 拨打电话:  点击图标拨打预设的电话号码\n");
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
