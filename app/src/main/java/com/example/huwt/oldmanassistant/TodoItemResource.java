package com.example.huwt.oldmanassistant;

import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageView;

public class TodoItemResource {


    int todoImgSrc; // 图片
    String todoTitle; // 标题
    String todoDetail; //


    // 构造方法
    public TodoItemResource(int todoImg, String todoTitle, String todoDetail){
        this.todoImgSrc = todoImg;
        this.todoTitle = todoTitle;
        this.todoDetail = todoDetail;
    }



}
