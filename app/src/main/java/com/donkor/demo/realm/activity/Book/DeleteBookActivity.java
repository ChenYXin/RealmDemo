package com.donkor.demo.realm.activity.Book;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.donkor.demo.realm.R;
import com.donkor.demo.realm.bean.Book;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmAsyncTask;
import io.realm.RealmResults;

/**
 * 异步删除学生页面
 */
public class DeleteBookActivity extends Activity {

    private ListView lvBooks;
    private RealmResults<Book> books;
    private Realm realm;
    private RealmAsyncTask deleteTask;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_book);

        lvBooks = (ListView) findViewById(R.id.lvBooks);
        realm = Realm.getDefaultInstance();
        //查询全部图书
        books = realm.where(Book.class).findAll();
        List<Book> bookList = realm.copyFromRealm(books);
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < bookList.size(); i++) {
            dataList.add(bookList.get(i).getName());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, dataList);
        lvBooks.setAdapter(adapter);

        lvBooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                deleteTask = realm.executeTransactionAsync(new Realm.Transaction() {
                    @Override
                    public void execute(Realm realm) {
                        String name = (String) ((TextView) view).getText();
                        Book book = realm.where(Book.class).equalTo("name", name).findFirst();
                        book.deleteFromRealm();
                        //在主线程中修改UI
                        RealmResults<Book> books = realm.where(Book.class).findAll();
                        List<Book> bookList = realm.copyFromRealm(books);
                        List<String> dataList = new ArrayList<>();
                        for (int i = 0; i < bookList.size(); i++) {
                            dataList.add(bookList.get(i).getName());
                        }
                        final ArrayAdapter adapter = new ArrayAdapter<>(DeleteBookActivity.this, android.R.layout.simple_expandable_list_item_1, dataList);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                lvBooks.setAdapter(adapter);
                            }
                        });
                    }
                }, new Realm.Transaction.OnSuccess() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(DeleteBookActivity.this, "Delete Success!", Toast.LENGTH_SHORT).show();
                    }
                }, new Realm.Transaction.OnError() {
                    @Override
                    public void onError(Throwable error) {
                        Toast.makeText(DeleteBookActivity.this, "Delete Error!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (deleteTask != null && deleteTask.isCancelled()) {
            deleteTask.cancel();
        }
    }
}