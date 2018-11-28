package com.example.huwt.oldmanassistant;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private ArrayList<TodoItemResource> rvItemData;

    public TodoAdapter(ArrayList<TodoItemResource> rvItemData){     // 构造方法, 设置初始数据
        this.rvItemData = rvItemData;
    }

    /**
     * 更新数据
     * @param newData 新的数据
     */
    public void updata(ArrayList<TodoItemResource> newData){
        this.rvItemData = newData;
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
        viewHolder.ivTodoImg.setImageResource(rvItemData.get(i).todoImgSrc);// 设置图片
        viewHolder.tvTodoTitle.setText(rvItemData.get(i).todoTitle);
        viewHolder.tvTodoDetail.setText(rvItemData.get(i).todoDetail);
    }

    @Override
    public int getItemCount() {
        return 0;
    }




}
