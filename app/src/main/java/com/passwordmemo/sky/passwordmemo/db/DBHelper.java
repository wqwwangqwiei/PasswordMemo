package com.passwordmemo.sky.passwordmemo.db;

import android.content.Context;

import com.passwordmemo.sky.passwordmemo.GlobalApp;
import com.passwordmemo.sky.passwordmemo.dao.AdminDao;
import com.passwordmemo.sky.passwordmemo.dao.DaoSession;
import com.passwordmemo.sky.passwordmemo.dao.SynDataDao;
import com.passwordmemo.sky.passwordmemo.dao.UserPasswordDao;

/**
 * Created by sky on 2015/11/7.
 */
public class DBHelper {
    private static final String TAG = DBHelper.class.getSimpleName();
    private static DBHelper instance;
    private static Context appContext;
    private DaoSession mDaoSession;
    private AdminDao adminDao;
    private UserPasswordDao userPasswordDao;
    private SynDataDao synDataDao;


    //单例模式，DBHelper只初始化一次
    public static  DBHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DBHelper();
            if (appContext == null){
                appContext = context.getApplicationContext();
            }
            //设置Session
            instance.mDaoSession = GlobalApp.getDaoSession(context);
            instance.adminDao = instance.mDaoSession.getAdminDao();
            instance.userPasswordDao = instance.mDaoSession.getUserPasswordDao();
            instance.synDataDao = instance.mDaoSession.getSynDataDao();

        }
        return instance;
    }

    //删除Admin表
    public void dropAdminTable(){
        AdminDao.dropTable(mDaoSession.getDatabase(), true);
    }

    //删除SynData表
    public void dropSynDataTable(){
        SynDataDao.dropTable(mDaoSession.getDatabase(),true);
    }

    //删除UserPassword表
    public void dropUserPasswordTable(){
        UserPasswordDao.dropTable(mDaoSession.getDatabase(),true);
    }

    //删除所有表
    public void dropAllTable(){
        AdminDao.dropTable(mDaoSession.getDatabase(), true);
        SynDataDao.dropTable(mDaoSession.getDatabase(),true);
        UserPasswordDao.dropTable(mDaoSession.getDatabase(),true);

    }

    //创建所有表
    public void createAllTable(){
        AdminDao.createTable(mDaoSession.getDatabase(), true);
        SynDataDao.createTable(mDaoSession.getDatabase(), true);
        UserPasswordDao.createTable(mDaoSession.getDatabase(), true);

    }













}
