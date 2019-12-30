package com.codingelab.tutorial;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static com.codingelab.tutorial.R.layout.activity_update;

public class UpdateData extends AppCompatActivity {
    private EditText editname,editphone,editemail;
    private Button update,cancel;
    private Syn syn;
    public static   String id;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_update);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
        this.syn = new Syn();
        Intent intent = getIntent();
        this.editname = (EditText) findViewById(R.id.editName);
        this.editphone = (EditText) findViewById(R.id.editPhone);
        this.editemail = (EditText) findViewById(R.id.editEmail);
        this.update = (Button) findViewById(R.id.btn_update);
        this.cancel = (Button) findViewById(R.id.btn_cancel);
        id = intent.getStringExtra("sid");
        String name = intent.getStringExtra("lname");
        String phone = intent.getStringExtra("lphone");
        String email = intent.getStringExtra("lemail");
        this.editname.setText(name);
        this.editphone.setText(phone);
        this.editemail.setText(email);
        this.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1 = editname.getText().toString();
                String phone1 = editphone.getText().toString();
                String email1 = editemail.getText().toString();
                syn.doInBackground("update",id,name1,phone1,email1);
            }
        });

        this.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(UpdateData.this,LoadDataActivity.class);
                startActivity(cancel);
            }
        });
    }

}
