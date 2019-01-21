package com.example.huwt.oldmanassistant;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.UnicodeSetSpanner;

import com.example.huwt.oldmanassistant.db.DbContract;
import com.example.huwt.oldmanassistant.db.TodoDbHelper;

/**
 * 为数据库添加演示数据
 */
public class DemoData {
    public static void importDemoData(Context context){
        TodoDemoData(context);
    }

    private static void TodoDemoData(Context context){
// 向todoLiYst数据库写入数据
        SQLiteDatabase mDb = new TodoDbHelper(context).getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(DbContract.ToDoEntry.COLUMN_TOTO_TITLE, "左右滑动移除提醒");
        cv.put(DbContract.ToDoEntry.COLUMN_TODO_DETAIL, "提醒详情内容");
        cv.put(DbContract.ToDoEntry.COLUMN_TODO_TYPE, TodoDbHelper.TODO_SPORT);
        mDb.insert(DbContract.ToDoEntry.TABLE_NAME, null, cv);
    }
}
