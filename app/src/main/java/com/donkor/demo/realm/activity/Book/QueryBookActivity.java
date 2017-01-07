package com.donkor.demo.realm.activity.Book;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.donkor.demo.realm.R;
import com.donkor.demo.realm.adapter.BookAdapter;
import com.donkor.demo.realm.bean.Book;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * 异步查询所有图书
 */
public class QueryBookActivity extends Activity {
    private ListView lvBooks;
    private RealmResults<Book> books;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_book);

        lvBooks = (ListView) findViewById(R.id.lvBooks);
        realm = Realm.getDefaultInstance();
        //查询全部图书
        books = realm.where(Book.class).findAllAsync();
        books.addChangeListener(callback);
    }

    private RealmChangeListener callback = new RealmChangeListener() {
        @Override
        public void onChange(Object element) {
            List<Book> bookList = realm.copyFromRealm(books);
            BookAdapter bookAdapter = new BookAdapter(QueryBookActivity.this, bookList);
            lvBooks.setAdapter(bookAdapter);
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销指定的监听
        books.removeChangeListener(callback);
        //注销所有监听
        books.removeChangeListeners();
    }
}