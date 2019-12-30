package com.codingelab.tutorial;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FormActivity extends Activity {
    private Button onAdd;
    private Syn syn;
    EditText name,phone,email;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

        }
        this.syn=new Syn();
        this.onAdd = (Button)findViewById(R.id.btn_Save);
        name = (EditText)findViewById(R.id.editName);
        phone = (EditText)findViewById(R.id.editPhone);
        email = (EditText)findViewById(R.id.editEmail);

        this.onAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = syn.doInBackground("insert", name.getText().toString(), phone.getText().toString(), email.getText().toString());
                Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();

                name.setText("");
                phone.setText("");
                email.setText("");
            }
        });

    }

}
