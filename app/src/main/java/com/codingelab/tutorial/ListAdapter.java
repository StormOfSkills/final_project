package com.codingelab.tutorial;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> ID;
    ArrayList<String> S_Name;
    ArrayList<String> S_Phone;
    ArrayList<String> S_Email;


    public ListAdapter(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> sub_name,
            ArrayList<String> Sub_Phone,
            ArrayList<String> Sub_Email
    )
    {

        this.context = context2;
        this.ID = id;
        this.S_Name = sub_name;
        this.S_Phone = Sub_Phone;
        this.S_Email = Sub_Email;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            child = layoutInflater.inflate(R.layout.items, null);

            holder = new Holder();

            holder.Name_TextView = (TextView) child.findViewById(R.id.textViewName);
            holder.Phone_TextView = (TextView) child.findViewById(R.id.textViewPhone);
            holder.Email_TextView = (TextView) child.findViewById(R.id.textViewEmail);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.Name_TextView.setText(S_Name.get(position));
        holder.Phone_TextView.setText(S_Phone.get(position));
        holder.Email_TextView.setText(S_Email.get(position));

        return child;
    }

    public class Holder {

        TextView Name_TextView;
        TextView Phone_TextView;
        TextView Email_TextView;
    }

}
