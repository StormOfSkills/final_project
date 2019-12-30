package com.codingelab.tutorial;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

import static com.codingelab.tutorial.R.layout.activity_searchremotly;

public class SearchRemotly extends AppCompatActivity {
    public static ArrayList<HashMap<String, String>> Items = new ArrayList<HashMap<String, String>>();
    ListView search ;
    EditText name;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_searchremotly);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }

        name = (EditText) findViewById(R.id.searchText);
        search = (ListView) findViewById(R.id.searchList);
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Items.clear();
                getJSON("http://192.168.43.8:8080/sqli/getdataid.php?name="+name.getText().toString());

            }

            @Override
            public void afterTextChanged(Editable s) {


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
        String[] heroes = new String[jsonArray.length()];
        for (int i = 0; i < jsonArray.length(); i++) {
            HashMap<String, String> map = new HashMap<String, String>();
            JSONObject obj = jsonArray.getJSONObject(i);
            String idd = heroes[i] = obj.getString("id");
            map.put("id", idd);
            String name = heroes[i] = obj.getString("name");
            map.put("name", name);
            String phone = heroes[i] = obj.getString("phone");
            map.put("phone", phone);
            String email = heroes[i] = obj.getString("email");
            map.put("email", email);
            Items.add(map);
        }
        final ListAdapter adapter = new SimpleAdapter(this, Items,
                R.layout.list_rows, new String[]{"id", "name", "phone","email"},
                new int[]{R.id.textId, R.id.textName, R.id.textPhone,R.id.textEmail});
        search.invalidateViews();
        search.setAdapter(adapter);
    }

}
