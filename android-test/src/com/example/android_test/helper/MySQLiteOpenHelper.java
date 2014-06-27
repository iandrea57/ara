/**
 * $Id$
 * Copyright 2011-2013 Oak Pacific Interactive. All rights reserved.
 */
package com.example.android_test.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * @author <a href="mailto:hailong.peng@renren-inc.com">彭海龙</a>
 * @createTime 14-3-23 下午5:01
 */
public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    public static final String TABLE_NAME = "andorid_test";
    public static final String ID = "id";
    public static final String TEXT = "text";
    public static final String DB_NAME = "andorid_test.db";
    public static final int VERSION = 1;

    public MySQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + TABLE_NAME + " ( " + ID
                + " integer primary key autoincrement, " + TEXT + " text );";
        sqLiteDatabase.execSQL(sql);
        Log.v("Ara", "MySQLiteOpenHelper.onCreate");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {
        Log.v("Ara", "MySQLiteOpenHelper.onUpgrade");
    }
}
