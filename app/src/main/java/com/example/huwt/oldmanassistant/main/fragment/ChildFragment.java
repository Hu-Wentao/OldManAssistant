package com.example.huwt.oldmanassistant.main.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huwt.oldmanassistant.R;
import com.example.huwt.oldmanassistant.TodoAdapter;
import com.example.huwt.oldmanassistant.db.TodoList;


public class ChildFragment extends Fragment {
    private View view;                                            // 定义view 来设置Fragment 的layout

    private RecyclerView rvTodo;
    private TodoAdapter todoAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 为该Fragment填充布局
        view = inflater.inflate(R.layout.fragment_child, container, false);

        initRecyclerView();
        return view;
    }


    private void initRecyclerView(){
        // 获取 查找 RecyclerView
        rvTodo = view.findViewById(R.id.rv_todo);

        // 创建Adapter
        todoAdapter = new TodoAdapter(getActivity(), TodoList.getTodoData()); // todo 在此处设置 TodoList内容

        // 设置Adapter
        rvTodo.setAdapter(todoAdapter);

        // 设置LayoutManager 为linearLayoutManager
        rvTodo.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        // 设置Item分割线 // 也可以在item的layout中实现
        //,,,,

        // 设置RecyclerView的监听事件


    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
//        mListener = null;
    }
}
