package com.donkor.demo.realm.activity.Student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.donkor.demo.realm.R;
import com.donkor.demo.realm.bean.Student;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * 根据学生用户名修改学生页面
 */
public class UpdateStudentActivity extends Activity {

    private ListView lvStudent;
    private Realm realm;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_student);

        lvStudent = (ListView) findViewById(R.id.lvStudent);
        realm = Realm.getDefaultInstance();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取全部学生对象并显示在ListView上
        RealmResults<Student> students = realm.where(Student.class).findAll();
        List<Student> studentList = realm.copyFromRealm(students);
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            dataList.add(studentList.get(i).getName());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, dataList);
        lvStudent.setAdapter(adapter);

        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) ((TextView) view).getText();
                Intent intent = new Intent(UpdateStudentActivity.this, StudentUpdateDetailActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}