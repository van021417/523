package com.login.dropdownmenu;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    String[] items= {"Dasma", "Imus","Indang", "Bacoor"};
    AutoCompleteTextView autoCompleteTxt;
    ArrayAdapter<String> adapterItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            autoCompleteTxt= findViewById(R.id.auto_complete_text);
            adapterItems= new ArrayAdapter<>(this, R.layout.list_item, items);
            autoCompleteTxt.setAdapter(adapterItems);
            autoCompleteTxt.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    String item= parent.getItemAtPosition(position).toString();
                    Toast.makeText(getApplicationContext(),"You have selected " + item+" as your destination.",Toast.LENGTH_SHORT).show();


                }
            });
        }
}

