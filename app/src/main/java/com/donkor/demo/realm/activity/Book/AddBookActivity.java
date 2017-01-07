package com.donkor.demo.realm.activity.Book;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.donkor.demo.realm.R;
import com.donkor.demo.realm.bean.Book;
import com.donkor.demo.realm.bean.Student;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

/**
 * 异步操作添加图书
 */
public class AddBookActivity extends Activity implements View.OnClickListener {
    private EditText etName, etAuthor, etPublishing;
    private Button btnAdd;
    private Realm realm;
    private RealmAsyncTask addTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addbook);

        etName = (EditText) findViewById(R.id.etName);
        etAuthor = (EditText) findViewById(R.id.etAuthor);
        etPublishing = (EditText) findViewById(R.id.etPublishing);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        //初始化
        realm = Realm.getDefaultInstance();

        btnAdd.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        addTask=realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Book book = new Book();
                book.setName(etName.getText().toString().trim());
                book.setAuthor(etAuthor.getText().toString().trim());
                book.setPublishing(etPublishing.getText().toString().trim());
                realm.copyToRealm(book);
            }
        },new Realm.Transaction.OnSuccess(){
            @Override
            public void onSuccess() {
                Toast.makeText(AddBookActivity.this,"Add success",Toast.LENGTH_SHORT).show();
                AddBookActivity.this.finish();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(AddBookActivity.this,"Add error",Toast.LENGTH_SHORT).show();
                etName.getText().clear();
                etAuthor.getText().clear();
                etPublishing.getText().clear();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消异步操作
        if(addTask!=null&&!addTask.isCancelled()){
            addTask.cancel();
        }
    }
}
