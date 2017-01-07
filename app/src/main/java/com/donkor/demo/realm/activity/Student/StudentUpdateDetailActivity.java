package com.donkor.demo.realm.activity.Student;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.donkor.demo.realm.R;
import com.donkor.demo.realm.bean.Student;

import io.realm.Realm;

/**
 * 修改学生页面
 */
public class StudentUpdateDetailActivity extends Activity {

    private Realm realm;
    private EditText etName, etPassword, etNickname;
    private Button btnUpdate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_update_detail);

        etName = (EditText) findViewById(R.id.etName);
        etPassword = (EditText) findViewById(R.id.etPwd);
        etNickname = (EditText) findViewById(R.id.etNickname);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        String name = getIntent().getStringExtra("name");

        realm = Realm.getDefaultInstance();

        final Student student = realm.where(Student.class).equalTo("name", name).findFirst();
        etName.setText(student.getName());
        etPassword.setText(student.getPassword() + "");
        etNickname.setText(student.getNickname());

        //第一种方式修改学生数据
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //使用事务块修改
                realm.executeTransaction(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        String newName = etName.getText().toString().trim();
                        String newPassword = etPassword.getText().toString().trim();
                        String newNickname = etNickname.getText().toString().trim();
                        student.setName(newName);
                        student.setPassword(Long.valueOf(newPassword));
                        student.setNickname(newNickname);
                        Toast.makeText(StudentUpdateDetailActivity.this, " Update student success!", Toast.LENGTH_SHORT).show();
                        StudentUpdateDetailActivity.this.finish();
                    }
                });
            }
        });
        //第二种方式修改学生数据
/*        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newName = etName.getText().toString().trim();
                String newPassword = etPassword.getText().toString().trim();
                String newNickname = etNickname.getText().toString().trim();

//                Log.e("asd", "newPassword: " + Long.valueOf(newPassword));
                //使用事务操作修改
                *//******************主要代码**************************//*
                realm.beginTransaction();
                student.setName(newName);
                student.setPassword(Long.valueOf(newPassword));
                student.setNickname(newNickname);
                realm.commitTransaction();
                *//******************主要代码**************************//*
                Toast.makeText(Student
                StudentUpdateDetailActivity.this, " Update student success!", Toast.LENGTH_SHORT).show();
                StudentUpdateDetailActivity.this.finish();
            }
        });*/
    }
}