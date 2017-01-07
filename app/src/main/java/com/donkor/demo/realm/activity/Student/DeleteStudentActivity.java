package com.donkor.demo.realm.activity.Student;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.donkor.demo.realm.R;
import com.donkor.demo.realm.bean.Student;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * 根据学生用户名删除学生页面
 */
public class DeleteStudentActivity extends Activity implements View.OnClickListener {

    private ListView lvStudent;
    private Realm realm;
    private ArrayAdapter adapter;
    private Button btnDelFirstStudent,btnDelLastStudent,btnDelNumStudent,btnDelAllStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_student);

        btnDelFirstStudent= (Button) findViewById(R.id.btnDelFirstStudent);
        btnDelLastStudent= (Button) findViewById(R.id.btnDelLastStudent);
        btnDelNumStudent= (Button) findViewById(R.id.btnDelNumStudent);
        btnDelAllStudent= (Button) findViewById(R.id.btnDelAllStudent);
        lvStudent = (ListView) findViewById(R.id.lvStudent);

        realm = Realm.getDefaultInstance();
        refresh();

        //第一种删除学生的方式
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                new AlertDialog.Builder(DeleteStudentActivity.this).setTitle("RealmDemo")//设置对话框标题
                        .setMessage("是否删除该学生")//设置显示的内容
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                //TODO Auto-generated method stub
                                final String name = (String) ((TextView) view).getText();
                                /******************主要代码**************************/
                                realm.beginTransaction();
                                Student student = realm.where(Student.class).equalTo("name", name).findFirst();
                                //指定student从数据库中删除
                                student.deleteFromRealm();
                                realm.commitTransaction();
                                /******************主要代码**************************/
                                refresh();
                                dialog.dismiss();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加返回按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//响应事件
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                }).show();//在按键响应事件中显示此对话框
            }
        });

        //第二种删除学生的方式
      /* lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                new AlertDialog.Builder(DeleteStudentActivity.this).setTitle("RealmDemo")//设置对话框标题
                        .setMessage("是否删除该学生")//设置显示的内容
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {//添加确定按钮
                            @Override
                            public void onClick(DialogInterface dialog, int which) {//确定按钮的响应事件
                                //TODO Auto-generated method stub
                                final String name = (String) ((TextView) view).getText();
                                *//******************主要代码**************************//*
                                realm.executeTransaction(new Realm.Transaction() {
                                    @Override
                                    public void execute(Realm realm) {
                                        Student student=realm.where(Student.class).equalTo("name",name).findFirst();
                                        //指定student从数据库中删除
                                        student.deleteFromRealm();
                                    }
                                });
                                *//******************主要代码**************************//*
                                refresh();
                                dialog.dismiss();
                            }
                        }).setNegativeButton("取消", new DialogInterface.OnClickListener() {//添加返回按钮
                    @Override
                    public void onClick(DialogInterface dialog, int which) {//响应事件
                        // TODO Auto-generated method stub
                        dialog.dismiss();
                    }
                }).show();//在按键响应事件中显示此对话框
            }
        });*/

        btnDelFirstStudent.setOnClickListener(this);
        btnDelLastStudent.setOnClickListener(this);
        btnDelNumStudent.setOnClickListener(this);
        btnDelAllStudent.setOnClickListener(this);
    }

    /**
     * 刷新页面
     */
    public void refresh() {
        //获取全部学生对象并显示在ListView上
        RealmResults<Student> students = realm.where(Student.class).findAll();
        List<Student> studentList = realm.copyFromRealm(students);
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < studentList.size(); i++) {
            dataList.add(studentList.get(i).getName());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, dataList);
        lvStudent.setAdapter(adapter);

    }

    @Override
    public void onClick(View v) {
        //删除同样可以用事务操作或者使用事务块两种方式，这里就写一种
        realm.beginTransaction();
        RealmResults<Student> rrStudents = realm.where(Student.class).findAll();
        switch (v.getId()){
            case R.id.btnDelFirstStudent:
                //删除第一个Student数据
                rrStudents.deleteFirstFromRealm();
                break;
            case R.id.btnDelLastStudent:
                //删除最后一个Student数据
                rrStudents.deleteLastFromRealm();
                break;
            case R.id.btnDelNumStudent:
                //删除位置为1的Student数据
                //坐标从0开始，0为第一项数据，1为第二项
                rrStudents.deleteFromRealm(1);
                break;
            case R.id.btnDelAllStudent:
                //删除所有Student数据
                rrStudents.deleteAllFromRealm();
                break;
            default:
                break;
        }
        realm.commitTransaction();
        //删除之后同样要刷新页面
        refresh();
    }
}
