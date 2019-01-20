package com.example.huwt.oldmanassistant.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * 为 TodoList 提供数据
 */
public class TodoDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "todo.db";
    private static final int DATABASE_VERSION = 2;
    // 使用常量取代枚举 为TODO_TYPE 设置选项值 以节约内存
    public static final int TODO_CLOCK = 0;
    public static final int TODO_MEDICINE = 1;
    public static final int TODO_SPORT = 1;

    public TodoDbHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_TODO_TABLE = "CREATE TABLE " + DbContract.ToDoEntry.TABLE_NAME + "(" +
                DbContract.ToDoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                DbContract.ToDoEntry.COLUMN_TOTO_TITLE + " TEXT NOT NULL, " +
                DbContract.ToDoEntry.COLUMN_TODO_DETAIL + " TEXT, " +
                DbContract.ToDoEntry.COLUMN_TODO_DEADLINE + " DATETIME, " +  // todo todo截止日期可以为空, 如果不为空, 需要使用定时器, 在todo到期后弹出提醒
                DbContract.ToDoEntry.COLUMN_TODO_TYPE + " INTEGERT NOT NULL " +
                ");";
        db.execSQL(SQL_CREATE_TODO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DbContract.ToDoEntry.TABLE_NAME);
        onCreate(db);
    }


}
