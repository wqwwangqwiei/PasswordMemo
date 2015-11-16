package com.passwordmemo.sky.passwordmemo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.devspark.appmsg.AppMsg;
import com.passwordmemo.sky.passwordmemo.dao.Admin;
import com.passwordmemo.sky.passwordmemo.dao.AdminDao;
import com.passwordmemo.sky.passwordmemo.dao.DaoSession;
import com.passwordmemo.sky.passwordmemo.db.DBHelper;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @Bind(R.id.login_register) TextView register;
    @Bind(R.id.login_btn) Button loginBtn;
    @Bind(R.id.user_name) EditText email;
    @Bind(R.id.password) EditText password;

    private DaoSession mDaoSession;
    private AdminDao adminDao;
    private SharedPreferences sp;


    final String Tag = "LoginActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        //载入记住的用户名和密码
        sp=this.getSharedPreferences("data", 0);
        reloadUserInfo();


        mDaoSession=GlobalApp.getDaoSession(this);
        adminDao=mDaoSession.getAdminDao();

        //-------重新建立数据库---------
        /*
        DBHelper helper=DBHelper.getInstance(this);
        helper.dropAllTable();
        helper.createAllTable();
        */
        //----------------------------

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(Tag, "启动注册页面");

                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Admin> admins= adminDao.queryBuilder().where(AdminDao.Properties.Email.eq(email.getText().toString())
                        ,AdminDao.Properties.Password.eq(password.getText().toString())).list();

                boolean isEmpty=admins.isEmpty();

                if(isEmpty){
                    AppMsg.makeText(LoginActivity.this, "登陆名或者密码错误", AppMsg.STYLE_ALERT).show();

                }else{
                    //验证通过，页面跳转，全局变量记住userid，并且记住用户名和密码
                    GlobalApp.userId=admins.get(0).getId();
                    rememberUserInfo();
                    Intent intent = new Intent(LoginActivity.this, ShowActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                }

            }
        });


    }

    private void reloadUserInfo(){
        String usernameStr=sp.getString("username",null);
        String passwordStr=sp.getString("password",null);
        if (usernameStr!=null && passwordStr !=null){
            email.setText(usernameStr);
            //password.setText(passwordStr);

        }
    }

    private void rememberUserInfo(){
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("username",email.getText().toString());
        editor.putString("password",password.getText().toString());
        editor.commit();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
