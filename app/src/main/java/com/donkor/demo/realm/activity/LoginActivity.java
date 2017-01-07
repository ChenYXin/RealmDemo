package com.donkor.demo.realm.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.donkor.demo.realm.R;

/**
 * @author donkor
 * 登录页面
 * blog：http://blog.csdn.net/donkor_
 * 个人微信公众号：donkor
 * 有问题可直接在公众号，博客上留言
 */
public class LoginActivity extends Activity {

    private EditText etAdmin,etPassword;
    private Button btnLogon;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etAdmin= (EditText) findViewById(R.id.etAdmin);
        etPassword= (EditText) findViewById(R.id.etPassword);
        btnLogon= (Button) findViewById(R.id.btnLogon);

        sharedPreferences= getSharedPreferences("Admin",
                Activity.MODE_PRIVATE);
        String isLogin=sharedPreferences.getString("isLogin","false");
        if(isLogin.equals("true")){
            Intent intent=new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);
            LoginActivity.this.finish();
        }


        btnLogon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String admin=etAdmin.getText().toString().trim();
                String pwd=etPassword.getText().toString().trim();
                if(TextUtils.isEmpty(admin)||TextUtils.isEmpty(pwd)){
                    Toast.makeText(LoginActivity.this,"Admin or password can't be empty!",Toast.LENGTH_LONG).show();
                    return;
                }
                if(admin.equals("donkor")&&pwd.equals("123456")){
                    //实例化SharedPreferences.Editor对象
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    //用putString的方法保存数据
                    editor.putString("isLogin","true");
                    //提交当前数据
                    editor.apply();

                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(intent);
                    LoginActivity.this.finish();
                    Toast.makeText(LoginActivity.this,"Login success!",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this,"Login error!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
