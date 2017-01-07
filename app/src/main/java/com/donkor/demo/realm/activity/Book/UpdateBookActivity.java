package com.donkor.demo.realm.activity.Book;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.donkor.demo.realm.R;
import com.donkor.demo.realm.bean.Book;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * 修改图书页面
 */
public class UpdateBookActivity extends Activity {

    private ListView lvBook;
    private Realm realm;
    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_book);

        lvBook = (ListView) findViewById(R.id.lvBook);
        realm = Realm.getDefaultInstance();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //获取全部学生对象并显示在ListView上
        RealmResults<Book> books = realm.where(Book.class).findAll();
        List<Book> bookList = realm.copyFromRealm(books);
        List<String> dataList = new ArrayList<>();
        for (int i = 0; i < bookList.size(); i++) {
            dataList.add(bookList.get(i).getName());
        }
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, dataList);
        lvBook.setAdapter(adapter);

        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String name = (String) ((TextView) view).getText();
                Intent intent = new Intent(UpdateBookActivity.this, BookUpdateDetailActivity.class);
                intent.putExtra("name", name);
                startActivity(intent);
            }
        });
    }
}