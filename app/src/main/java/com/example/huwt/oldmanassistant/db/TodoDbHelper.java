package com.example.huwt.oldmanassistant.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 为 TodoList 提供数据
 */
public class TodoDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 1;
    public TodoDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TODO_TABLE = "CREATE TABLE " + DATABASE_NAME + "(" +
                DbContract.ToDoEntry._ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbContract.ToDoEntry.COLUMN_TOTO_TITLE + "TEXT NOT NULL, " +
                DbContract.ToDoEntry.COLUMN_TODO_DETAIL + "TEXT, " +
                DbContract.ToDoEntry.COLUMN_TODO_DEADLINE + "DATETIME, " +  // todo todo截止日期可以为空, 如果不为空, 需要使用定时器, 在todo到期后弹出提醒
                DbContract.ToDoEntry.COLUMN_TODO_TYPE + "TEXT" +          // 通过文字来辨别todo的类型 //todo 可能需要创建一个 enum来识别todo的类型
                ");";
        db.execSQL(SQL_CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_NAME);
        onCreate(db);
    }
}
