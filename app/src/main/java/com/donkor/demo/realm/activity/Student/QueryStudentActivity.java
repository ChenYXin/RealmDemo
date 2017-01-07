package com.donkor.demo.realm.activity.Student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.donkor.demo.realm.R;
import com.donkor.demo.realm.bean.Student;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * 查询学生
 */
public class QueryStudentActivity extends Activity {
    private ListView lvStudent;
    private Button btnQueryStudent;
    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_querystudent);

        etName= (EditText) findViewById(R.id.etName);
        btnQueryStudent= (Button) findViewById(R.id.btnQueryStudent);
        lvStudent = (ListView) findViewById(R.id.lvStudent);
        Realm realm = Realm.getDefaultInstance();
        //查询全部学生
        RealmResults<Student> students = realm.where(Student.class).findAll();

        List<Student> studentList = realm.copyFromRealm(students);
        List<String> dataList=new ArrayList<>();
        for(int i=0;i<studentList.size();i++){
            dataList.add(studentList.get(i).getName());
        }



        lvStudent.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1,dataList));
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) ((TextView) view).getText();
                Intent intent=new Intent(QueryStudentActivity.this,StudentDetailActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
        btnQueryStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=etName.getText().toString().trim();
                Intent intent=new Intent(QueryStudentActivity.this,StudentDetailActivity.class);
                intent.putExtra("name",name);
                startActivity(intent);
            }
        });
    }
}
