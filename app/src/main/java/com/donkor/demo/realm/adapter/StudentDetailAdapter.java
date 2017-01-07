package com.donkor.demo.realm.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.donkor.demo.realm.R;
import com.donkor.demo.realm.bean.Book;

import io.realm.RealmList;

public class StudentDetailAdapter extends BaseAdapter {

    private Context mContext;
    private RealmList<Book> bookRealmList;
    private LayoutInflater inflater;

    public StudentDetailAdapter(Context context, RealmList<Book> bookRealmList) {
        this.mContext = context;
        this.bookRealmList = bookRealmList;

    }

    @Override
    public int getCount() {
        return bookRealmList.size();
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
//        Log.e("asd","name:"+bookRealmList.get(position).getName());
        viewHolder.tvName.setText("Name:    " + bookRealmList.get(position).getName());
        viewHolder.tvAuthor.setText("Author:    " + bookRealmList.get(position).getAuthor());
        viewHolder.tvPublishing.setText("Publishing:    " + bookRealmList.get(position).getPublishing());

        return convertView;
    }

    public class ViewHolder {
        TextView tvName;
        TextView tvAuthor;
        TextView tvPublishing;
    }
}
