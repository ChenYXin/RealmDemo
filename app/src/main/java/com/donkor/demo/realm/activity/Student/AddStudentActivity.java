package com.donkor.demo.realm.activity.Student;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.donkor.demo.realm.R;
import com.donkor.demo.realm.bean.Book;
import com.donkor.demo.realm.bean.Student;

import io.realm.Realm;

/**
 * 添加学生
 */
public class AddStudentActivity extends Activity implements View.OnClickListener {
    private EditText etName, etPwd, etNickname;
    private Button btnAdd;
    private CheckBox cbBook1, cbBook2, cbBook3, cbBook4;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudent);

        cbBook1 = (CheckBox) findViewById(R.id.cbBook1);
        cbBook2 = (CheckBox) findViewById(R.id.cbBook2);
        cbBook3 = (CheckBox) findViewById(R.id.cbBook3);
        cbBook4 = (CheckBox) findViewById(R.id.cbBook4);
        etName = (EditText) findViewById(R.id.etName);
        etPwd = (EditText) findViewById(R.id.etPwd);
        etNickname = (EditText) findViewById(R.id.etNickname);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        //初始化
        realm = Realm.getDefaultInstance();

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        realm.beginTransaction();
        Student student = realm.createObject(Student.class);

        //没有选中图书给出吐司提示
        if(!cbBook1.isChecked()&&!cbBook2.isChecked()&&!cbBook3.isChecked()&&!cbBook4.isChecked()){
            Toast.makeText(AddStudentActivity.this,"请选择添加的图书",Toast.LENGTH_SHORT).show();
            return;
        }
        //添加选中的图书
        if (cbBook1.isChecked()) {
            Book book = new Book();
            book.setName("第一行代码");
            book.setAuthor("路人甲");
            book.setPublishing("图灵");
            student.getBooks().add(book);
        }
        if (cbBook2.isChecked()) {
            Book book = new Book();
            book.setName("Object-C基础教程");
            book.setAuthor("路人乙");
            book.setPublishing("人民电邮");
            student.getBooks().add(book);
        }
        if (cbBook3.isChecked()) {
            Book book = new Book();
            book.setName("java入门");
            book.setAuthor("路人丙");
            book.setPublishing("清华大学");
            student.getBooks().add(book);
        }
        if (cbBook4.isChecked()) {
            Book book = new Book();
            book.setName("php精通");
            book.setAuthor("路人丁");
            book.setPublishing("人民教育");
            student.getBooks().add(book);
        }
        //用户名
        String name = etName.getText().toString().trim();
        //密码
        String pwd = etPwd.getText().toString().trim();
        //姓名
        String nickname = etNickname.getText().toString().trim();
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd) || TextUtils.isEmpty(nickname)) {
            Toast.makeText(AddStudentActivity.this, "Name or password or nickname can't be empty!", Toast.LENGTH_LONG).show();
            return;
        }
        /******************以下三种方式对数据库进行增加操作,自己选择个合适的**************************/
        /******************使用事务操作**************************/
        /******************方法一，添加一个新的Student对象到数据库start**************************/

        student.setName(name);
        student.setPassword(Long.parseLong(pwd));
        student.setNickname(nickname);
        realm.commitTransaction();
        /******************添加end**************************/
        /******************方法二，复制一个新的Student对象到数据库start**************************/
//                realm.beginTransaction();
//                Student student=new Student();
//                student.setName(name);
//                student.setPassword(Long.parseLong(pwd));
//                student.setNickname(nickname);
//                realm.copyToRealm(student);
//                realm.commitTransaction();
        /******************复制end**************************/
        /******************使用事务块操作**************************/
//                final Student student = new Student();
//                student.setName(name);
//                student.setPassword(Long.parseLong(pwd));
//                student.setNickname(nickname);
//                realm.executeTransaction(new Realm.Transaction() {
//                    @Override
//                    public void execute(Realm realm) {
//                        realm.copyToRealm(student);
//                    }
//                });
        Toast.makeText(AddStudentActivity.this, " Add student success!", Toast.LENGTH_SHORT).show();
        AddStudentActivity.this.finish();
    }
}
