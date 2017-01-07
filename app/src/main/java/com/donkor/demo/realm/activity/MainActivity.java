package com.donkor.demo.realm.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.donkor.demo.realm.R;
import com.donkor.demo.realm.activity.Book.BookActivity;
import com.donkor.demo.realm.activity.Student.StudentActivity;

/**
 * 主页面，管理页面
 */
public class MainActivity extends Activity implements View.OnClickListener {

    private Button btnStudent,btnBook;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStudent= (Button) findViewById(R.id.btnStudent);
        btnBook= (Button) findViewById(R.id.btnBook);

        btnStudent.setOnClickListener(this);
        btnBook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent=null;
        switch (v.getId()){
            case R.id.btnStudent:
                intent=new Intent(MainActivity.this,StudentActivity.class);
                startActivity(intent);
                break;
            case R.id.btnBook:
                intent=new Intent(MainActivity.this,BookActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
