package com.example.huwt.oldmanassistant.db;

import android.content.Context;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huwt.oldmanassistant.R;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private Context mContext;
    private Cursor mCursor;
    public TodoAdapter(Context context, Cursor cursor){     // 构造方法, 设置初始数据    // todo 获取了Context, 但未使用
        this.mCursor = cursor;
        this.mContext = context;
    }

    /**
     * 更新数据
     * @param newCursor 新的数据
     */
    public void updata(Cursor newCursor){
        if(this.mCursor != null)this.mCursor.close();

        this.mCursor = newCursor;
        if(newCursor != null){
            this.notifyDataSetChanged();
        }
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder{
        ImageView ivTodoImg;
        TextView tvTodoTitle;
        TextView tvTodoDetail;

        public TodoViewHolder(@NonNull View itemView) {
            super(itemView);
            ivTodoImg = itemView.findViewById(R.id.rv_iv_item_img);
            tvTodoTitle = itemView.findViewById(R.id.rv_tv_item_title);
            tvTodoDetail = itemView.findViewById(R.id.rv_tv_item_detail);
            // todo 添加一个todo截止日期
        }
    }


    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_item, viewGroup, false);
        // 实例化ViewHolder
        TodoAdapter.TodoViewHolder todoViewHolder = new TodoViewHolder(v);
        return todoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.TodoViewHolder viewHolder, int i) {
        // 绑定数据
        if(!mCursor.moveToPosition(i)) return;
        // 标题
        String title = mCursor.getString(mCursor.getColumnIndex(DbContract.ToDoEntry.COLUMN_TOTO_TITLE));
        viewHolder.tvTodoTitle.setText(title);
        // 详情
        String detail = mCursor.getString(mCursor.getColumnIndex(DbContract.ToDoEntry.COLUMN_TODO_DETAIL));
        viewHolder.tvTodoDetail.setText(detail);
        // todo类型
        int type = mCursor.getInt(mCursor.getColumnIndex(DbContract.ToDoEntry.COLUMN_TODO_TYPE));
        viewHolder.ivTodoImg.setImageDrawable(getImgFromIntTODOType(type, mContext));// 通过不同的数值为RecyclerView中相应的Item设置不同的图标

        // todo 为todo加入截止日期
        String deadLine = mCursor.getString(mCursor.getColumnIndex(DbContract.ToDoEntry.COLUMN_TODO_DEADLINE));

        // todo的id
        long id = mCursor.getLong(mCursor.getColumnIndex(DbContract.ToDoEntry._ID));
        viewHolder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }



    private Drawable getImgFromIntTODOType(int type, Context context){
        switch (type){
            case 0:
                return context.getDrawable(R.drawable.todo_ic_clock);
            case 1:
                return context.getDrawable(R.drawable.todo_ic_medicine);
            case 2:
                return context.getDrawable(R.drawable.todo_ic_sport);
        }
        throw new RuntimeException("没有相应的case");
    }

}
