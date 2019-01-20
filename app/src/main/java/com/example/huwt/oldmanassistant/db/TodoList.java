package com.example.huwt.oldmanassistant.db;

import com.example.huwt.oldmanassistant.R;
import com.example.huwt.oldmanassistant.TodoItemResource;

import java.util.ArrayList;

/**
 * 用于测试, //todo 使用数据库取代本类
 */
public class TodoList {

    public static ArrayList<TodoItemResource> getTodoData(){
        ArrayList<TodoItemResource> src = new ArrayList<>(3);
        src.add(new TodoItemResource(R.drawable.todo_ic_clock, "一个提醒标题", "一个提醒内容"));
        src.add(new TodoItemResource(R.drawable.todo_ic_medicine, "该吃药的提醒", "一个吃药提醒内容"));
        src.add(new TodoItemResource(R.drawable.todo_ic_sport, "一个该运动的", "一个运动提醒内容"));


        return src;
    }


}
