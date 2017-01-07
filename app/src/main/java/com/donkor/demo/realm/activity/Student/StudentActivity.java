package com.donkor.demo.realm.activity.Student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.donkor.demo.realm.R;

/**
 * 学生管理页面
 */
public class StudentActivity extends Activity implements View.OnClickListener {

    private Button btnAddStudent,btnQueryStudent,btnDeleteStudent,btnUpdateStudent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        btnAddStudent= (Button) findViewById(R.id.btnAddStudent);
        btnUpdateStudent= (Button) findViewById(R.id.btnUpdateStudent);
        btnQueryStudent= (Button) findViewById(R.id.btnQueryStudent);
        btnDeleteStudent= (Button) findViewById(R.id.btnDeleteStudent);
        btnDeleteStudent.setOnClickListener(this);
        btnQueryStudent.setOnClickListener(this);
        btnUpdateStudent.setOnClickListener(this);
        btnAddStudent.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.btnQueryStudent:
                intent=new Intent(StudentActivity.this,QueryStudentActivity.class);
                startActivity(intent);
                break;
            case R.id.btnAddStudent:
                intent=new Intent(StudentActivity.this,AddStudentActivity.class);
                startActivity(intent);
                break;
            case R.id.btnDeleteStudent:
                intent=new Intent(StudentActivity.this,DeleteStudentActivity.class);
                startActivity(intent);
                break;
            case R.id.btnUpdateStudent:
                intent=new Intent(StudentActivity.this,UpdateStudentActivity.class);
                startActivity(intent);
            break;
            default:
                break;
        }
    }
}
