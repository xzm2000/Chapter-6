package com.byted.camp.todolist;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.byted.camp.todolist.beans.State;
import com.byted.camp.todolist.db.TodoContract;
import com.byted.camp.todolist.db.TodoDbHelper;

import java.sql.Time;

public class NoteActivity extends AppCompatActivity {

    private EditText editText;
    private Button addBtn;
    private RadioGroup prioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        setTitle(R.string.take_a_note);

        editText = findViewById(R.id.edit_text);
        editText.setFocusable(true);
        editText.requestFocus();
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.showSoftInput(editText, 0);
        }


        prioGroup = findViewById(R.id.radio_group);

        addBtn = findViewById(R.id.btn_add);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int priority = 1;
                if(prioGroup.getCheckedRadioButtonId() == R.id.btn_low)
                    priority = 1;
                else if(prioGroup.getCheckedRadioButtonId() == R.id.btn_medium)
                    priority = 2;
                else if(prioGroup.getCheckedRadioButtonId() == R.id.btn_high)
                    priority = 3;
                CharSequence content = editText.getText();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(NoteActivity.this,
                            "No content to add", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean succeed = saveNote2Database(content.toString().trim(), priority);
                if (succeed) {
                    Toast.makeText(NoteActivity.this,
                            "Note added", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                } else {
                    Toast.makeText(NoteActivity.this,
                            "Error", Toast.LENGTH_SHORT).show();
                }
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private boolean saveNote2Database(String content, int priority) {
        // DONE 插入一条新数据，返回是否插入成功

        long date = System.currentTimeMillis();

        TodoDbHelper todoDbHelper = new TodoDbHelper(getApplicationContext());
        SQLiteDatabase db = todoDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(TodoContract.TodoEntry.CONTENT, content);
        values.put(TodoContract.TodoEntry.STATE, State.TODO.intValue);
        values.put(TodoContract.TodoEntry.DATE, date);
        values.put(TodoContract.TodoEntry.PRIORITY, priority);

        long newRow_Id = -1;
        try {
            newRow_Id = db.insert(TodoContract.TodoEntry.TABLE_NAME, null, values);
        }finally {
            todoDbHelper.close();
        }
        return (newRow_Id != -1);
    }
}
