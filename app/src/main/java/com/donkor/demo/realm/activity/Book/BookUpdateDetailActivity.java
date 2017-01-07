package com.donkor.demo.realm.activity.Book;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.donkor.demo.realm.R;
import com.donkor.demo.realm.bean.Book;

import io.realm.Realm;
import io.realm.RealmAsyncTask;

/**
 * 修改图书页面
 */
public class BookUpdateDetailActivity extends Activity implements View.OnClickListener {

    private Realm realm;
    private EditText etName, etAuthor, etPublishing;
    private Button btnUpdate;
    private RealmAsyncTask updateTask;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_update_detail);

        etName = (EditText) findViewById(R.id.etName);
        etAuthor = (EditText) findViewById(R.id.etAuthor);
        etPublishing = (EditText) findViewById(R.id.etPublishing);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        realm = Realm.getDefaultInstance();

        name = getIntent().getStringExtra("name");
        Book book = realm.where(Book.class).equalTo("name", name).findFirst();
        etName.setText(book.getName());
        etAuthor.setText(book.getAuthor());
        etPublishing.setText(book.getPublishing());

        btnUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        updateTask = realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Book book = realm.where(Book.class).equalTo("name", name).findFirst();
                book.setName(etName.getText().toString().trim());
                book.setAuthor(etAuthor.getText().toString().trim());
                book.setPublishing(etPublishing.getText().toString().trim());
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Toast.makeText(BookUpdateDetailActivity.this,"Update success",Toast.LENGTH_SHORT).show();
                BookUpdateDetailActivity.this.finish();
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Toast.makeText(BookUpdateDetailActivity.this,"Update error",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (updateTask != null && updateTask.isCancelled()) {
            updateTask.cancel();
        }
    }
}