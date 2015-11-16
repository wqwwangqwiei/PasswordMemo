package com.passwordmemo.sky.passwordmemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.devspark.appmsg.AppMsg;
import com.passwordmemo.sky.passwordmemo.dao.Admin;
import com.passwordmemo.sky.passwordmemo.dao.AdminDao;
import com.passwordmemo.sky.passwordmemo.dao.DaoSession;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by sky on 2015/10/28.
 */
public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.submit) Button submit;
    @Bind(R.id.txt_email) EditText email;
    @Bind(R.id.txt_password) EditText password;
    @Bind(R.id.txt_password_again) EditText passwordAgain;

    private DaoSession mDaoSession;
    private AdminDao adminDao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();
        submit.setOnClickListener(this);

    }

    //初始化Dao组件
    private void init() {
        mDaoSession= GlobalApp.getDaoSession(this);
        adminDao=mDaoSession.getAdminDao();
    }

    @Override
    public void onClick(View v) {
        //1.检查密码输入是否一致
        if(!password.getText().toString().equals(passwordAgain.getText().toString())){
            AppMsg.makeText(RegisterActivity.this,"密码不一致，请重新输入",AppMsg.STYLE_ALERT).show();
            return;
        }

        //2.检查数据库是否存在
        if(!adminDao.queryBuilder().where(AdminDao.Properties.Email.eq(email.getText().toString())).list().isEmpty()){
            AppMsg.makeText(RegisterActivity.this,"该用户已经存在",AppMsg.STYLE_ALERT).show();
            return;
        }

        //3.存入数据库
        long aa=adminDao.insert(new Admin(null,email.getText().toString(),password.getText().toString()));
        AppMsg.makeText(RegisterActivity.this,"用户注册成功",AppMsg.STYLE_ALERT).show();

    }
}
