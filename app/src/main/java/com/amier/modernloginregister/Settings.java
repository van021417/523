package com.amier.modernloginregister;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Settings extends AppCompatActivity {
    private String sharedpref="modernloginregister";

    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    String id, name, email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        TextView textView2 =findViewById(R.id.textView2);
        sharedPreferences=getSharedPreferences(sharedpref, MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putBoolean("loggedin",false);
        editor.apply();
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in=new Intent(Settings.this,Log.class);
                startActivity(in);
                finish();
            }
        });

    }
}

