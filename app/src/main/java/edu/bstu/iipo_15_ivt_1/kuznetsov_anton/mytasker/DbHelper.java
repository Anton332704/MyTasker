package edu.bstu.iipo_15_ivt_1.kuznetsov_anton.mytasker;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by user on 22.05.2016.
 */
public class DbHelper extends SQLiteOpenHelper {

    public static final String TASK_TABLE = "task_table";
    public static final String GROUP_TABLE = "group_table";
    public static final String USER_TABLE = "user_table";

    public static final String TASK_TITLE = "task_title";
    public static final String TASK_STATUS = "task_status";
    public static final String TASK_INFO = "task_info";
    public static final String TASK_DATE_FINISH = "task_date_finish";
    public static final String TASK_DATE_START = "task_date_start";
    public static final String TASK_ID_GROUP = "task_id_group";


    public static final String GROUP_NAME = "group_name";

    public static final String USER_NAME = "user_name";
    public static final String USER_PASSWORD = "user_passw";


    public static final String MAKE_TASK_TABLE = "CREATE TABLE " + TASK_TABLE + " (_id integer primary key autoincrement, " +
                                                    TASK_TITLE + " text, " +  TASK_STATUS + " integer, " +
                                                    TASK_INFO + " text, " + TASK_DATE_FINISH + " text, " +
                                                    TASK_DATE_START + " text, " + TASK_ID_GROUP + " integer);";

    public static final String MAKE_GROUP_TABLE = "CREATE TABLE " + GROUP_TABLE + " (_id integer primary key, " +
                                                    GROUP_NAME + " text);";

    public static final String MAKE_USER_TABLE = "CREATE TABLE " + USER_TABLE + " (_id integer primary key, " +
                                             USER_NAME + " text, " + USER_PASSWORD + " text);";

    public DbHelper(Context context) {
        super(context, "task_database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MAKE_TASK_TABLE);
        db.execSQL(MAKE_GROUP_TABLE);
        db.execSQL(MAKE_USER_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}