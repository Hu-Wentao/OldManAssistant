package com.example.huwt.oldmanassistant.db;

import android.provider.BaseColumns;

public class DbContract {
    // 用户信息
    public static final class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_USER_ACCOUNT = "userName";
        public static final String COLUMN_USER_PWD = "userPwd";
        public static final String COLUMN_USER_SEX = "userSex";     // 真男假女
        public static final String COLUMN_USER_TYPE = "userType";   // (真老假小) 老人 | 子女
        public static final String COLUMN_USER_BIRTHDAY = "userBirthday"; //出生日期
        public static final String COLUMN_USER_PHONE = "userPhone"; // 手机号
        public static final String COLUMN_USER_ADDRESS = "userAddress"; // 家庭住址
    }

    // 待办事项信息
    public static final class ToDoEntry implements BaseColumns {
        public static final String TABLE_NAME = "todo";
        public static final String COLUMN_TODO_TYPE = "todoType"; // todo的类型, 据此判断使用不同的图标
        public static final String COLUMN_TOTO_TITLE = "todoTitle";
        public static final String COLUMN_TODO_DETAIL = "todoDetail"; // todo的详情
        public static final String COLUMN_TODO_DEADLINE = "todoDeadline"; // todo的截止日期
    }
}
