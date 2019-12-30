package com.codingelab.tutorial;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class listviewAdapter extends BaseAdapter {

    public ArrayList<Person> personlist;
    Activity activity;

    public listviewAdapter(Activity activity, ArrayList<Person> personlist) {
        super();
        this.activity = activity;
        this.personlist = personlist;
    }

    @Override
    public int getCount() {
        return personlist.size();
    }

    @Override
    public Object getItem(int position) {
        return personlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder {
        TextView mSid;
        TextView mName;
        TextView mPhone;
        TextView mEmail;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.listview_row, null);
            holder = new ViewHolder();
            holder.mSid = (TextView) convertView.findViewById(R.id.idText);
            holder.mName = (TextView) convertView.findViewById(R.id.NameText);
            holder.mPhone = (TextView) convertView
                    .findViewById(R.id.PhoneText);
            holder.mEmail = (TextView) convertView.findViewById(R.id.EmailText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Person item = personlist.get(position);
        holder.mSid.setText(item.getId().toString());
        holder.mName.setText(item.getName().toString());
        holder.mPhone.setText(item.getPhone().toString());
        holder.mEmail.setText(item.getEmail().toString());
        return convertView;
    }
}
