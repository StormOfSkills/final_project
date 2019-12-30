package com.codingelab.tutorial;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;



public class SyncActivity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabase;

    ImageView SaveButtonInSQLite, ShowSQLiteDataInListView;
    String HttpJSonURL = "http://192.168.43.8:8080/sqli/syncdata.php";

    ProgressDialog progressDialog;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
        SaveButtonInSQLite = (ImageView) findViewById(R.id.imageViewLocal);
        ShowSQLiteDataInListView = (ImageView) findViewById(R.id.imageViewOnline);
        SaveButtonInSQLite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDataBaseBuild();
                SQLiteTableBuild();
                DeletePreviousData();
                new StoreJSonDataInToSQLiteClass(SyncActivity.this).execute();

            }
        });

        ShowSQLiteDataInListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SyncActivity.this, ShowDataActivity.class);
                startActivity(intent);
            }
        });
    }
    private class StoreJSonDataInToSQLiteClass extends AsyncTask<Void, Void, Void> {
        public Context context;
        String FinalJSonResult;
        public StoreJSonDataInToSQLiteClass(Context context) {
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(SyncActivity.this);
            progressDialog.setTitle("Loading...");
            progressDialog.setMessage("Please Wait...");
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpServiceClass httpServiceClass = new HttpServiceClass(HttpJSonURL);
            try {
                httpServiceClass.ExecutePostRequest();
                if (httpServiceClass.getResponseCode() == 200) {
                    FinalJSonResult = httpServiceClass.getResponse();
                    if (FinalJSonResult != null) {
                        JSONArray jsonArray = null;
                        try {
                            jsonArray = new JSONArray(FinalJSonResult);
                            JSONObject jsonObject;
                            for (int i = 0; i < jsonArray.length(); i++) {
                                jsonObject = jsonArray.getJSONObject(i);
                                String tempName = jsonObject.getString("name");
                                String tempPhone= jsonObject.getString("phone");
                                String tempEmail= jsonObject.getString("email");
                                String SQLiteDataBaseQueryHolder = "INSERT INTO "+SQLiteHelper.TABLE_NAME+" (name,phone,email) VALUES('"+tempName+"', '"
                                        +tempPhone+"', '"+tempEmail+"');";
                                sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                } else {

                    Toast.makeText(context, httpServiceClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            sqLiteDatabase.close();
            progressDialog.dismiss();
            Toast.makeText(SyncActivity.this,"Done", Toast.LENGTH_LONG).show();
        }
    }
    public void DeletePreviousData(){
        sqLiteDatabase.execSQL("DELETE FROM "+SQLiteHelper.TABLE_NAME+"");

    }
    public void SQLiteDataBaseBuild(){
        sqLiteDatabase = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
    }
    public void SQLiteTableBuild(){
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS "+SQLiteHelper.TABLE_NAME
                +"("+SQLiteHelper.Table_Column_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+SQLiteHelper.Table_Column_Name+" VARCHAR, "
                +SQLiteHelper.Table_Column_Phone+" VARCHAR,"+SQLiteHelper.Table_Column_Email+" VARCHAR);");
    }


}
