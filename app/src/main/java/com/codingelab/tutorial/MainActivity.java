package com.codingelab.tutorial;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private Button onInsert;
    private Button onSyn;
    private Button onAdd;
    private Button onLoad;
    private ImageView sync;
    private Syn syn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        // initilizing propreties
        this.onInsert=(Button)findViewById(R.id.onInsert);
        this.onSyn=(Button)findViewById(R.id.onSyn);
        this.syn=new Syn();
        // preparing listener (onAction)
        this.onInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=syn.doInBackground("insert","Musaad","059993929","mj@gmail.com");
                Toast.makeText(getBaseContext(),msg,Toast.LENGTH_SHORT).show();
            }
        });
        this.onSyn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                syn.doInBackground("syn");
            }
        });
        this.onAdd = (Button) findViewById(R.id.btn_Add);
        this.onAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent add = new Intent(MainActivity.this ,FormActivity.class);
                startActivity(add);

            }
        });
        this.onLoad = (Button) findViewById(R.id.btn_Load);
        this.onLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent Load = new Intent(MainActivity.this ,LoadDataActivity.class);
                startActivity(Load);
            }
        });

        this.sync = (ImageView) findViewById(R.id.imageView);

        this.sync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sync = new Intent(MainActivity.this ,SyncActivity.class);
                startActivity(sync);
            }
        });

    }
}
