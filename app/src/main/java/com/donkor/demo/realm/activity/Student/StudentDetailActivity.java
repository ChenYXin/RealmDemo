package com.donkor.demo.realm.activity.Student;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.donkor.demo.realm.R;
import com.donkor.demo.realm.adapter.StudentDetailAdapter;
import com.donkor.demo.realm.bean.Book;
import com.donkor.demo.realm.bean.Student;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * 查询学生详情页
 */
public class StudentDetailActivity extends Activity {

    private TextView tvName, tvPassword, tvNickname;
    private ListView lvBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_detail);

        lvBooks = (ListView) findViewById(R.id.lvBooks);
        tvName = (TextView) findViewById(R.id.tvName);
        tvPassword = (TextView) findViewById(R.id.tvPassword);
        tvNickname = (TextView) findViewById(R.id.tvNickname);

        String name = getIntent().getStringExtra("name");
        Realm realm = Realm.getDefaultInstance();
        //根据name查找第一个student信息
        Student student = realm.where(Student.class).equalTo("name", name).findFirst();
//        Log.e("asd", "getName()：" + student.getName());
//        Log.e("asd", "getPassword():" + student.getPassword());
//        Log.e("asd", "getNickname(): " + student.getNickname());
        tvName.setText(student.getName());
        tvPassword.setText(student.getPassword() + "");
        tvNickname.setText(student.getNickname());
        RealmList<Book> rrBooks = student.getBooks();
//        Log.e("asd", "rrBooks.size(): " + rrBooks.size());
//        for(Book b:rrBooks){
//            Log.e("asd", "b.getName(): " + b.getName());
//        }
        StudentDetailAdapter studentDetailAdapter = new StudentDetailAdapter(StudentDetailActivity.this, rrBooks);
        lvBooks.setAdapter(studentDetailAdapter);
    }
}
