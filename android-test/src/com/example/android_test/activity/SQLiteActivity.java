/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.example.android_test.activity;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.example.android_test.R;
import com.example.android_test.helper.MySQLiteOpenHelper;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-23 下午5:06
 */
public class SQLiteActivity extends Activity implements View.OnClickListener {

    private Button btn_addOne, btn_deleteOne, btn_check, btn_deleteTable,
            btn_edit, btn_newTable;
    private TextView tv;
    private MySQLiteOpenHelper myOpenHelper;
    private SQLiteDatabase db;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.sqlitelayout);

        tv = (TextView) findViewById(R.id.tv_title);

        btn_addOne = (Button) findViewById(R.id.sql_addOne);
        btn_check = (Button) findViewById(R.id.sql_check);
        btn_deleteOne = (Button) findViewById(R.id.sql_deleteOne);
        btn_deleteTable = (Button) findViewById(R.id.sql_deleteTable);
        btn_newTable = (Button) findViewById(R.id.sql_newTable);
        btn_edit = (Button) findViewById(R.id.sql_edit);

        btn_addOne.setOnClickListener(this);
        btn_check.setOnClickListener(this);
        btn_deleteOne.setOnClickListener(this);
        btn_deleteTable.setOnClickListener(this);
        btn_newTable.setOnClickListener(this);
        btn_edit.setOnClickListener(this);

        myOpenHelper = new MySQLiteOpenHelper(this);
    }

    @Override
    public void onClick(View v) {
        try {
            db = myOpenHelper.getWritableDatabase();
            if (v == btn_addOne) {
                ContentValues cv = new ContentValues();
                cv.put(MySQLiteOpenHelper.TEXT, "测试新数据");
                db.insert(MySQLiteOpenHelper.TABLE_NAME, null, cv);
                tv.setText("添加数据成功! 点击查看数据库查询");
            } else if (v == btn_deleteOne) {
                db.delete(MySQLiteOpenHelper.TABLE_NAME,
                        MySQLiteOpenHelper.ID + "=1", null);
                tv.setText("删除数据成功! 点击查看数据库查询");
            } else if (v == btn_check) {
                Cursor cursor = db.rawQuery(
                        "select * from " + MySQLiteOpenHelper.TABLE_NAME, null);
                if (cursor != null) {
                    String temp = "";
                    int i = 0;
                    while (cursor.moveToNext()) {
                        temp += cursor.getString(0);
                        temp += cursor.getString(1);
                        i++;
                        temp += "  ";
                        if (i % 3 == 0) {
                            temp += "\n";
                        }
                    }
                    tv.setText(temp);
                }
            } else if (v == btn_edit) {
                ContentValues cv = new ContentValues();
                cv.put(MySQLiteOpenHelper.TEXT, "修改后的数据");
                db.update(MySQLiteOpenHelper.TABLE_NAME, cv,
                        MySQLiteOpenHelper.ID + " = 3", null);
                tv.setText("修改数据成功! 点击查看数据库查询");
            } else if (v == btn_deleteTable) {
                db.execSQL("drop table " + MySQLiteOpenHelper.TABLE_NAME);
                tv.setText("删除表成功! 点击查看数据库查询");
            } else if (v == btn_newTable) {
                String table = MySQLiteOpenHelper.TABLE_NAME;
                String id = MySQLiteOpenHelper.ID;
                String text = MySQLiteOpenHelper.TEXT;
                String sql = "create table " + table + " ( " + id
                        + " integer primary key autoincrement, " + text
                        + " text );";
                db.execSQL(sql);
                tv.setText("新建表成功! 点击查看数据库查询");
            }
        } catch (SQLException e) {
            Log.e("Ara", e.getMessage());
            tv.setText("操作失败");
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
}
