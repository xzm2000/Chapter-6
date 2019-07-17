package com.byted.camp.todolist.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created on 2019/1/22.
 *
 * @author xuyingyi@bytedance.com (Yingyi Xu)
 */
public class TodoDbHelper extends SQLiteOpenHelper {

    // DONE 定义数据库名、版本；创建数据库

    public static final String DATABASE_NAME = "Todo.db";
    public TodoDbHelper(Context context) {
        super(context, "todo", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TodoContract.CREATE_TODOLIST);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion <= 1 && newVersion > 1){
            db.execSQL("ALTER TABLE " + TodoContract.TodoEntry.TABLE_NAME + " ADD " + TodoContract.TodoEntry.PRIORITY + " INTEGER");
        }
    }

}
