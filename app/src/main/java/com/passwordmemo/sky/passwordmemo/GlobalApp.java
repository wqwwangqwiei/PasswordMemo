package com.passwordmemo.sky.passwordmemo;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.passwordmemo.sky.passwordmemo.dao.DaoMaster;
import com.passwordmemo.sky.passwordmemo.dao.DaoSession;

/**
 * Created by sky on 2015/11/7.
 */
public class GlobalApp extends Application{

    private static DaoMaster daoMaster;
    private static DaoSession daoSession;
    public static  SQLiteDatabase db;
    //数据库名，表名是自动被创建的
    public static final String DB_NAME = "iDemo";

    public static Long userId=0l;   //保存登录用户id


    //获得daoMaster对象
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }


    public static DaoSession getDaoSession(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }


    public static SQLiteDatabase getSQLDatebase(Context context) {
        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            db = daoMaster.getDatabase();
        }
        return db;
    }


    @Override
    public void onCreate() {
        super.onCreate();
    }


}
