package com.codingelab.tutorial;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.codingelab.tutorial.R.layout.activity_loaddata;

public class LoadDataActivity extends AppCompatActivity {
    private Syn syn;
    ListView SubjectListView;
    List<String> usersList;
    Button delete,update,search;

    public static String sid,lname,lphone,lemil;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_loaddata);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
        this.syn=new Syn();
        this.getJSON("http://192.168.43.8:8080/sqli/getdata.php");
        SubjectListView = (ListView) findViewById(R.id.listuser);
        update = (Button) findViewById(R.id.btn_Update);
        delete = (Button) findViewById(R.id.btn_Delete);
        search = (Button) findViewById(R.id.btn_Search);

        usersList= new ArrayList<>();

        SubjectListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                sid = ((TextView) view.findViewById(R.id.textId)).getText().toString();
                lname = ((TextView) view.findViewById(R.id.textName)).getText().toString();
                lphone = ((TextView) view.findViewById(R.id.textPhone)).getText().toString();
                lemil = ((TextView) view.findViewById(R.id.textEmail)).getText().toString();
                String msg = "Record Selected: "+sid+" "+lname+" "+lphone+" "+lemil;
                Toast.makeText(getBaseContext(),msg,Toast.LENGTH_SHORT).show();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(LoadDataActivity.this ,UpdateData.class);
                add.putExtra("sid",sid);
                add.putExtra("lname",lname);
                add.putExtra("lphone",lphone);
                add.putExtra("lemail",lemil);
                startActivity(add);
                Toast.makeText(getBaseContext(),"Record Updated",Toast.LENGTH_SHORT).show();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg = syn.doInBackground("delete",sid);
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();
                Toast.makeText(getBaseContext(),"Record Deleted",Toast.LENGTH_SHORT).show();
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent search = new Intent(LoadDataActivity.this,SearchRemotly.class);
                startActivity(search);
            }
        });
    }

    private void getJSON(final String urlWebService) {

        class GetJSON extends AsyncTask<Void, Void, String> {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    loadIntoListView(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL(urlWebService);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    StringBuilder sb = new StringBuilder();
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String json;
                    while ((json = bufferedReader.readLine()) != null) {
                        sb.append(json + "\n");
                    }
                    return sb.toString().trim();
                } catch (Exception e) {
                    return null;
                }
            }
        }
        GetJSON getJSON = new GetJSON();
        getJSON.execute();

    }

    private void loadIntoListView(String json) throws JSONException {
        JSONArray jsonArray = new JSONArray(json);
        ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id",obj.getString("id"));
            map.put("name",obj.getString("name"));
            map.put("phone",obj.getString("phone"));
            map.put("email",obj.getString("email"));
            Items.add(map);
        }
        ListAdapter myadapter = new SimpleAdapter(this, Items,
                R.layout.list_rows, new String[]{"id","name", "phone", "email"},
                new int[]{R.id.textId,R.id.textName, R.id.textPhone, R.id.textEmail});
        SubjectListView.setAdapter(myadapter);
    }

}

