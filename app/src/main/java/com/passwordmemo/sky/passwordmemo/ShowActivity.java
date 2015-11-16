package com.passwordmemo.sky.passwordmemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.passwordmemo.sky.passwordmemo.adapter.MemoAdapter;
import com.passwordmemo.sky.passwordmemo.dao.DaoSession;
import com.passwordmemo.sky.passwordmemo.dao.UserPassword;
import com.passwordmemo.sky.passwordmemo.dao.UserPasswordDao;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by sky on 2015/11/10.
 */
public class ShowActivity extends AppCompatActivity {

    @Bind(R.id.list_memo) ListView listMemo;
    @Bind(R.id.fab_add) FloatingActionButton addBtn; //添加记录按钮
    @Bind(R.id.fab_menu) FloatingActionMenu fabMenu;

    private DaoSession mDaoSession;
    private UserPasswordDao userPasswordDao;

    List<UserPassword> listdata;                    //Adapter数据源
    MemoAdapter memoAdapter;

    LayoutInflater inflater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        ButterKnife.bind(this);
        inflater = getLayoutInflater();


        mDaoSession = GlobalApp.getDaoSession(this);
        userPasswordDao = mDaoSession.getUserPasswordDao();

        listMemo.addHeaderView(inflater.inflate(R.layout.adapter_account_item, null));

        listdata = getDataList();//只查出相关的

        memoAdapter = new MemoAdapter(ShowActivity.this, listdata);
        listMemo.setAdapter(memoAdapter);


        final View dialogAccount = inflater.inflate(R.layout.dialog_account, (ViewGroup) findViewById(R.id.dialog_account));

        final EditText account_describe = (EditText) dialogAccount.findViewById(R.id.account_describe);
        final EditText account_username = (EditText) dialogAccount.findViewById(R.id.account_username);
        final EditText account_password = (EditText) dialogAccount.findViewById(R.id.account_password);


        final AlertDialog dialog = new AlertDialog.Builder(ShowActivity.this).setTitle("写入信息")
                .setView(dialogAccount)
                .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        userPasswordDao.insert(new UserPassword(null,
                                GlobalApp.userId,
                                account_describe.getText().toString(),
                                account_username.getText().toString(),
                                account_password.getText().toString()));

                        account_describe.getText().clear();
                        account_username.getText().clear();
                        account_password.getText().clear();

                        refresh();
                        Log.i(ShowActivity.class.getName(),
                                account_username.getText().toString() + " " + account_password.getText().toString());
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.i(ShowActivity.class.getName(), "取消写入");
                    }
                })
                .create();

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fabMenu.close(true);
                Log.i(ShowActivity.class.getName(), "添加一条账号密码数据。");
                dialog.show();
            }
        });

    }


    /**
     * 刷新列表
     */
    public void refresh() {
        //首先要更新数据源，然后再更新适配器
        listdata.clear();
        listdata.addAll(getDataList());
        memoAdapter.notifyDataSetChanged();
        Log.i(ShowActivity.class.getName(), "刷新操作");
    }

    /**
     * 数据查询
     * @return 数据集合
     */
    public List<UserPassword> getDataList(){
        return userPasswordDao.queryBuilder().where(UserPasswordDao.Properties.AdminId.eq(GlobalApp.userId)).list();
    }

    /**
     * 注销登录
     */
    @OnClick(R.id.logout)
    public void logout(){
        Intent intent=new Intent(ShowActivity.this,LoginActivity.class);
        startActivity(intent);
        ShowActivity.this.finish();
    }
}





















