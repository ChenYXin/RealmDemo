package com.donkor.demo.realm.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.donkor.demo.realm.R;
import com.donkor.demo.realm.bean.Book;

import java.util.List;

public class BookAdapter extends BaseAdapter {

    private Context mContext;
    private List<Book> bookList;
    private LayoutInflater inflater;

    public BookAdapter(Context context, List<Book>bookList) {
        this.mContext = context;
        this.bookList = bookList;

    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(R.layout.item_student_detail,
                    parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tvName);
            viewHolder.tvAuthor = (TextView) convertView.findViewById(R.id.tvAuthor);
            viewHolder.tvPublishing = (TextView) convertView.findViewById(R.id.tvPublishing);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
//        Log.e("asd","name:"+bookList.get(position).getName());
        viewHolder.tvName.setText("Name:    " + bookList.get(position).getName());
        viewHolder.tvAuthor.setText("Author:    " + bookList.get(position).getAuthor());
        viewHolder.tvPublishing.setText("Publishing:    " + bookList.get(position).getPublishing());

        return convertView;
    }

    public class ViewHolder {
        TextView tvName;
        TextView tvAuthor;
        TextView tvPublishing;
    }
}
