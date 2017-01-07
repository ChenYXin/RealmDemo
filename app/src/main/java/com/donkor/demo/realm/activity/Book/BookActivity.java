package com.donkor.demo.realm.activity.Book;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.donkor.demo.realm.R;

/**
 * 图书管理页面
 */
public class BookActivity extends Activity implements View.OnClickListener {

    private Button btnAddBook, btnQueryBook, btnDeleteBook,btnUpdateBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        btnUpdateBook = (Button) findViewById(R.id.btnUpdateBook);
        btnAddBook = (Button) findViewById(R.id.btnAddBook);
        btnQueryBook = (Button) findViewById(R.id.btnQueryBook);
        btnDeleteBook = (Button) findViewById(R.id.btnDeleteBook);
        btnUpdateBook.setOnClickListener(this);
        btnAddBook.setOnClickListener(this);
        btnQueryBook.setOnClickListener(this);
        btnDeleteBook.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btnAddBook:
                intent = new Intent(BookActivity.this, AddBookActivity.class);
                startActivity(intent);
                break;
            case R.id.btnQueryBook:
                intent = new Intent(BookActivity.this, QueryBookActivity.class);
                startActivity(intent);
                break;
            case R.id.btnDeleteBook:
                intent = new Intent(BookActivity.this, DeleteBookActivity.class);
                startActivity(intent);
                break;
            case R.id.btnUpdateBook:
                intent = new Intent(BookActivity.this, UpdateBookActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
