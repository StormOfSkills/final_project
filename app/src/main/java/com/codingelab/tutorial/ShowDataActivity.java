package com.codingelab.tutorial;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowDataActivity extends AppCompatActivity {

    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter ;
    ListView LISTVIEW;
    ArrayList<String> ID_Array;
    ArrayList<String> NAME_Array;
    ArrayList<String> Phone_Array;
    ArrayList<String> Emai_Array;

    ArrayList<String> ListViewClickItemArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_data);

        LISTVIEW = (ListView) findViewById(R.id.listView1);

        ID_Array = new ArrayList<String>();

        NAME_Array = new ArrayList<String>();

        Phone_Array = new ArrayList<String>();
        Emai_Array = new ArrayList<String>();

        sqLiteHelper = new SQLiteHelper(this);

        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub

                Toast.makeText(ShowDataActivity.this, ListViewClickItemArray.get(position).toString(), Toast.LENGTH_LONG).show();

            }
        });

    }

    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME+"", null);

        ID_Array.clear();
        NAME_Array.clear();
        Phone_Array.clear();
        Emai_Array.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));

                //Inserting Column Name into Array to Use at ListView Click Listener Method.
                ListViewClickItemArray.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_Name)));

                NAME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_Name)));

                Phone_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_Phone)));
                Emai_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_Email)));



            } while (cursor.moveToNext());
        }

        listAdapter = new ListAdapter(ShowDataActivity.this,

                ID_Array,
                NAME_Array,
                Phone_Array,
                Emai_Array
        );

        LISTVIEW.setAdapter(listAdapter);

        cursor.close();
    }
}
