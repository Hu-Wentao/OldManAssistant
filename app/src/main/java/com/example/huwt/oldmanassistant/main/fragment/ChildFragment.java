package com.example.huwt.oldmanassistant.main.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.huwt.oldmanassistant.R;
import com.example.huwt.oldmanassistant.db.TodoAdapter;
import com.example.huwt.oldmanassistant.db.DbContract;
import com.example.huwt.oldmanassistant.db.TodoDbHelper;


public class ChildFragment extends Fragment {
    private View view;                                            // 定义view 来设置Fragment 的layout

    private RecyclerView rvTodo;
    private TodoAdapter todoAdapter;
    private SQLiteDatabase mDb;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 为该Fragment填充布局
        view = inflater.inflate(R.layout.fragment_child, container, false);

        mDb = new TodoDbHelper(getContext()).getReadableDatabase();
        initRecyclerView();
        return view;
    }


    private void initRecyclerView(){
        // 获取 查找 RecyclerView
        rvTodo = view.findViewById(R.id.rv_todo);

        // 创建Adapter
        todoAdapter = new TodoAdapter(getActivity(), getTodoList()); // 在此处设置 TodoList内容

        // 设置Adapter
        rvTodo.setAdapter(todoAdapter);

        // 设置LayoutManager 为linearLayoutManager
        rvTodo.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));

        // 设置Item分割线 // 也可以在item的layout中实现
        //,,,,

        // 设置RecyclerView的点击监听事件


    }

    // 获取 所有todoList
    private Cursor getTodoList(){
        return mDb.query(DbContract.ToDoEntry.TABLE_NAME,
        new String[]{DbContract.ToDoEntry._ID,              // id
                DbContract.ToDoEntry.COLUMN_TOTO_TITLE,     // title
                DbContract.ToDoEntry.COLUMN_TODO_DETAIL,    // detail
                DbContract.ToDoEntry.COLUMN_TODO_TYPE,      // type
                DbContract.ToDoEntry.COLUMN_TODO_DEADLINE}, // deadline
                null,
                null,
                null,
                null,
                DbContract.ToDoEntry._ID);
    }




}
