package com.byted.camp.todolist.db;

import android.provider.BaseColumns;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public final class TodoContract {

    // DONE 定义表结构和 SQL 语句常量
    public static class TodoEntry implements BaseColumns {
        public static final String TABLE_NAME = "todolist";
        public static final String DATE = "date";
        public static final String STATE = "state";
        public static final String CONTENT= "content";
        public static final String PRIORITY = "priority";
    }

    public static final String CREATE_TODOLIST =
            "CREATE TABLE " + TodoEntry.TABLE_NAME +" (" +
                    TodoEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TodoEntry.DATE + " INTEGER," +
                    TodoEntry.STATE + " INTEGER," +
                    TodoEntry.CONTENT + " TEXT," +
                    TodoEntry.PRIORITY + " INTEGER)";

    public static final String DELETE_TODOLIST =
            "DROP TABLE IF EXISTS " + TodoEntry.TABLE_NAME;

    private TodoContract() {
    }

}
