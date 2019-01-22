package com.example.huwt.oldmanassistant.main.fragment;

import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.huwt.oldmanassistant.R;
import com.example.huwt.oldmanassistant.db.TodoAdapter;
import com.example.huwt.oldmanassistant.db.DbContract;
import com.example.huwt.oldmanassistant.db.TodoDbHelper;


public class ChildFragment extends Fragment implements View.OnClickListener {
    private View view;                                            // 定义view 来设置Fragment 的layout

    private RecyclerView rvTodo;
    private TodoAdapter todoAdapter;
    private SQLiteDatabase mDb;

    private TextView mLeftCallTv;
    private TextView mRightCallTv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // 为该Fragment填充布局
        view = inflater.inflate(R.layout.fragment_child, container, false);

        mDb = new TodoDbHelper(getContext()).getReadableDatabase();
        // 两个拨号按钮
        mLeftCallTv = view.findViewById(R.id.tv_contact1);
        mLeftCallTv.setOnClickListener(this);
        mRightCallTv = view.findViewById(R.id.tv_contact2);
        mRightCallTv.setOnClickListener(this);
        initRecyclerView();

        // 添加todo移除功能
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override    // 无需关注onMove功能
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override   // 配置滑动操作
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                long id = (long) viewHolder.itemView.getTag(); //获取id
                removeTodoItem(id);                             // 从数据库删除数据
                todoAdapter.updata(getTodoList());              // 更新recyclerView
            }
        }).attachToRecyclerView(rvTodo);

        return view;
    }

    // 初始化todoList
    private void initRecyclerView() {
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
    private Cursor getTodoList() {
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

    // 删除指定ID的Item
    private boolean removeTodoItem(long id) {
        return mDb.delete(DbContract.ToDoEntry.TABLE_NAME,
                DbContract.ToDoEntry._ID + "=" + id, null) > 0;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_contact1: // todo 添加一个设置拨号号码的功能按钮, 可以将号码存储到用户数据中
                callPhone("17681339426");
                break;
            case R.id.tv_contact2:
                callPhone("15256036171");
                break;
            default:
                throw new RuntimeException("未指定case");
        }
    }

    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
}
